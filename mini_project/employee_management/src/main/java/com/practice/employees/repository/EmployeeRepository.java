package com.practice.employees.repository;

import com.practice.employees.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    @EntityGraph(attributePaths = "department")
    List<Employee> findAll();

    @Query("""
            select e from Employee e join fetch e.department d
            where lower(e.name) like lower(concat('%', :keyword, '%'))
               or lower(d.name) like lower(concat('%', :keyword, '%'))
            order by e.name
            """)
    List<Employee> search(@Param("keyword") String keyword);

    @Query("""
            select d.name as department, count(e) as employeeCount
            from Department d left join Employee e on e.department = d
            group by d.id, d.name order by d.name
            """)
    List<DepartmentCount> countByDepartment();
}
