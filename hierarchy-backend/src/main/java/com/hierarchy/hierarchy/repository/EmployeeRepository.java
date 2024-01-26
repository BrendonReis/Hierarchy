package com.hierarchy.hierarchy.repository;

import com.hierarchy.hierarchy.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByName(String name);
}