package com.practice.springcore.repository;

import com.practice.springcore.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(long id);
    boolean deleteById(long id);
}
