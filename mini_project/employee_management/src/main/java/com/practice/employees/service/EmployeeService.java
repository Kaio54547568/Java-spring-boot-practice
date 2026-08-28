package com.practice.employees.service;

import com.practice.employees.domain.Employee;
import com.practice.employees.dto.EmployeeRequest;
import com.practice.employees.exception.ResourceNotFoundException;
import com.practice.employees.repository.DepartmentCount;
import com.practice.employees.repository.DepartmentRepository;
import com.practice.employees.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employees;
    private final DepartmentRepository departments;
    private final UtilityService utility;

    public EmployeeService(EmployeeRepository employees, DepartmentRepository departments, UtilityService utility) {
        this.employees = employees; this.departments = departments; this.utility = utility;
    }

    public List<Employee> findAll(String keyword) {
        return keyword == null || keyword.isBlank() ? employees.findAll() : employees.search(keyword.trim());
    }

    public Employee findById(long id) {
        return employees.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee %d not found".formatted(id)));
    }

    @Transactional @CacheEvict(value = "employeeCount", allEntries = true)
    public Employee create(EmployeeRequest request) {
        var department = departments.findById(request.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        var employee = new Employee(utility.generateEmployeeCode(), utility.normalize(request.name()),
                utility.normalizeEmail(request.email()), department);
        try {
            employee = employees.save(employee);
            log.info("Created employee {}", employee.getCode());
            return employee;
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Employee email already exists", ex);
        }
    }

    @Transactional @CacheEvict(value = "employeeCount", allEntries = true)
    public Employee update(long id, EmployeeRequest request) {
        var employee = findById(id);
        var department = departments.findById(request.departmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        employee.update(utility.normalize(request.name()), utility.normalizeEmail(request.email()), department);
        log.info("Updated employee {}", employee.getCode());
        return employee;
    }

    @Transactional @CacheEvict(value = "employeeCount", allEntries = true)
    public void delete(long id) {
        var employee = findById(id);
        employees.delete(employee);
        log.info("Deleted employee {}", employee.getCode());
    }

    @Cacheable("employeeCount")
    public long count() { return employees.count(); }

    public List<DepartmentCount> statistics() { return employees.countByDepartment(); }
}
