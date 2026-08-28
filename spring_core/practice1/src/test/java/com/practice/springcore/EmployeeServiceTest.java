package com.practice.springcore;

import com.practice.springcore.config.ApplicationConfig;
import com.practice.springcore.domain.Employee;
import com.practice.springcore.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {
    @Test
    void createsAndReadsEmployeeThroughJdbcTemplate() {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            var service = context.getBean(EmployeeService.class);
            service.create(new Employee(null, "Pham Lan", "lan@example.com", "Sales"));
            assertEquals(3, service.findAll().size());
        }
    }

    @Test
    void rejectsInvalidEmail() {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            var service = context.getBean(EmployeeService.class);
            assertThrows(IllegalArgumentException.class,
                    () -> service.create(new Employee(null, "Pham Lan", "invalid", "Sales")));
        }
    }
}
