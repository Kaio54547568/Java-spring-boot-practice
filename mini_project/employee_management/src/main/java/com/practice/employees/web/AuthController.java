package com.practice.employees.web;

import com.practice.employees.dto.RegisterRequest;
import com.practice.employees.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService users;
    public AuthController(UserService users) { this.users = users; }

    @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> register(@Valid @RequestBody RegisterRequest request) {
        var user = users.register(request);
        return Map.of("username", user.getUsername(), "role", user.getRole().name());
    }
    @GetMapping("/me")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of("username", authentication.getName(), "authorities", authentication.getAuthorities());
    }
}
