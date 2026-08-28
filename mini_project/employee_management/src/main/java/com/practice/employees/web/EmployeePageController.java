package com.practice.employees.web;

import com.practice.employees.dto.EmployeeRequest;
import com.practice.employees.repository.DepartmentRepository;
import com.practice.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {
    private final EmployeeService employees;
    private final DepartmentRepository departments;
    public EmployeePageController(EmployeeService employees, DepartmentRepository departments) {
        this.employees = employees; this.departments = departments;
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("employees", employees.findAll(keyword));
        model.addAttribute("keyword", keyword);
        return "employees/list";
    }
    @GetMapping("/add") @PreAuthorize("hasRole('ADMIN')")
    public String addForm(Model model) {
        model.addAttribute("employeeRequest", new EmployeeRequest("", "", null));
        model.addAttribute("departments", departments.findAll());
        return "employees/form";
    }
    @PostMapping("/add") @PreAuthorize("hasRole('ADMIN')")
    public String add(@Valid @ModelAttribute EmployeeRequest employeeRequest, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("departments", departments.findAll());
            return "employees/form";
        }
        employees.create(employeeRequest);
        return "redirect:/employees/list";
    }
    @GetMapping("/statistics")
    public String statistics(Model model) {
        model.addAttribute("total", employees.count());
        model.addAttribute("statistics", employees.statistics());
        return "employees/statistics";
    }
}
