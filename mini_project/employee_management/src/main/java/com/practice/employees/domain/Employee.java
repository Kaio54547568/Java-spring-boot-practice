package com.practice.employees.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "employees")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false, length = 100)
    private String code;
    @NotBlank @Column(nullable = false, length = 150)
    private String name;
    @NotBlank @Email @Column(nullable = false, unique = true, length = 200)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    protected Employee() {}
    public Employee(String code, String name, String email, Department department) {
        this.code = code; this.name = name; this.email = email; this.department = department;
    }
    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Department getDepartment() { return department; }
    public void update(String name, String email, Department department) {
        this.name = name; this.email = email; this.department = department;
    }
}
