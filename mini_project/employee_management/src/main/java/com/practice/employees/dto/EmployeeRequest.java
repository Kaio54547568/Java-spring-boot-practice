package com.practice.employees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(
        @NotBlank(message = "Name must not be blank") String name,
        @NotBlank(message = "Email must not be blank") @Email(message = "Email is invalid") String email,
        @NotNull(message = "Department is required") Long departmentId) {
}
