package com.practice.employees.dto;

import com.practice.employees.domain.Employee;

public record EmployeeResponse(Long id, String code, String name, String email,
                               Long departmentId, String departmentName) {
    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getCode(), employee.getName(), employee.getEmail(),
                employee.getDepartment().getId(), employee.getDepartment().getName());
    }
}
