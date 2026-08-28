package com.practice.employees.service;

import com.practice.employees.domain.AppUser;
import com.practice.employees.domain.Role;
import com.practice.employees.dto.RegisterRequest;
import com.practice.employees.exception.ConflictException;
import com.practice.employees.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    public UserService(UserRepository users, PasswordEncoder encoder) { this.users = users; this.encoder = encoder; }

    @Transactional
    public AppUser register(RegisterRequest request) {
        String username = request.username().trim();
        if (users.existsByUsername(username)) throw new ConflictException("Username already exists");
        return users.save(new AppUser(username, encoder.encode(request.password()), Role.USER));
    }
}
