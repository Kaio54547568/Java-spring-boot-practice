package com.practice.employees.config;

import com.practice.employees.domain.*;
import com.practice.employees.repository.DepartmentRepository;
import com.practice.employees.repository.EmployeeRepository;
import com.practice.employees.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seedData(DepartmentRepository departments, EmployeeRepository employees,
                               UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (departments.count() == 0) {
                var engineering = departments.save(new Department("Engineering"));
                var hr = departments.save(new Department("Human Resources"));
                employees.save(new Employee("EMP-000001", "Nguyen Van An", "an@example.com", engineering));
                employees.save(new Employee("EMP-000002", "Tran Thu Ha", "ha@example.com", hr));
            }
            if (users.count() == 0) {
                users.save(new AppUser("admin", encoder.encode("Admin@123"), Role.ADMIN));
                users.save(new AppUser("user", encoder.encode("User@123"), Role.USER));
            }
        };
    }
}
