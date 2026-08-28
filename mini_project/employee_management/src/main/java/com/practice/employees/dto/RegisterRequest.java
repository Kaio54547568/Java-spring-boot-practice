package com.practice.employees.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 8, message = "Password must contain at least 8 characters") String password) {
}
