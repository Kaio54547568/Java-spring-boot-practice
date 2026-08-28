package com.practice.springcore;

import com.practice.springcore.config.ApplicationConfig;
import com.practice.springcore.domain.Employee;
import com.practice.springcore.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Application {
    private Application() {
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            var service = context.getBean(EmployeeService.class);
            service.create(new Employee(null, "Le Minh Khoa", "khoa@example.com", "Finance"));
            service.findAll().forEach(System.out::println);
        }
    }
}
