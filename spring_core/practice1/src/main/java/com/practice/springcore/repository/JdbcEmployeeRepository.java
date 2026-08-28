package com.practice.springcore.repository;

import com.practice.springcore.domain.Employee;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
    private final JdbcClient jdbcClient;
    private final SimpleJdbcInsert employeeInsert;

    public JdbcEmployeeRepository(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcClient = JdbcClient.create(jdbcTemplate);
        this.employeeInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("employees")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.id() == null) {
            Number id = employeeInsert.executeAndReturnKey(Map.of(
                    "name", employee.name(),
                    "email", employee.email(),
                    "department", employee.department()));
            return new Employee(id.longValue(), employee.name(), employee.email(), employee.department());
        }
        jdbcClient.sql("UPDATE employees SET name = ?, email = ?, department = ? WHERE id = ?")
                .params(employee.name(), employee.email(), employee.department(), employee.id()).update();
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return jdbcClient.sql("SELECT id, name, email, department FROM employees ORDER BY id")
                .query(Employee.class).list();
    }

    @Override
    public Optional<Employee> findById(long id) {
        return jdbcClient.sql("SELECT id, name, email, department FROM employees WHERE id = ?")
                .param(id).query(Employee.class).optional();
    }

    @Override
    public boolean deleteById(long id) {
        return jdbcClient.sql("DELETE FROM employees WHERE id = ?").param(id).update() > 0;
    }
}
