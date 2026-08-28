package com.practice.employees.web;

import com.practice.employees.repository.DepartmentCount;
import com.practice.employees.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final EmployeeService employees;
    public ReportController(EmployeeService employees) { this.employees = employees; }
    @GetMapping("/employee-count")
    public Map<String, Long> count() { return Map.of("totalEmployees", employees.count()); }
    @GetMapping("/by-department")
    public List<DepartmentCount> byDepartment() { return employees.statistics(); }
}
