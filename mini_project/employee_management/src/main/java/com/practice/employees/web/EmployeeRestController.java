package com.practice.employees.web;

import com.practice.employees.dto.EmployeeRequest;
import com.practice.employees.dto.EmployeeResponse;
import com.practice.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService service;
    public EmployeeRestController(EmployeeService service) { this.service = service; }

    @GetMapping
    public List<EmployeeResponse> list(@RequestParam(required = false) String keyword) {
        return service.findAll(keyword).stream().map(EmployeeResponse::from).toList();
    }
    @GetMapping("/{id}")
    public EmployeeResponse get(@PathVariable long id) { return EmployeeResponse.from(service.findById(id)); }
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponse.from(service.create(request)));
    }
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable long id, @Valid @RequestBody EmployeeRequest request) {
        return EmployeeResponse.from(service.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id); return ResponseEntity.noContent().build();
    }
}
