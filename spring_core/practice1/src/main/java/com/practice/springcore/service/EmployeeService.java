package com.practice.springcore.service;

import com.practice.springcore.domain.Employee;
import com.practice.springcore.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final Clock clock;

    public EmployeeService(EmployeeRepository repository, Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    public Employee create(Employee employee) {
        validate(employee);
        return repository.save(employee);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Instant currentTime() {
        return clock.instant();
    }

    private void validate(Employee employee) {
        if (employee.name() == null || employee.name().isBlank()) {
            throw new IllegalArgumentException("Employee name must not be blank");
        }
        if (employee.email() == null || !employee.email().contains("@")) {
            throw new IllegalArgumentException("Employee email is invalid");
        }
    }
}
