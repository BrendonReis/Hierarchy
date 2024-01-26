package com.hierarchy.hierarchy.service;

import com.hierarchy.hierarchy.entity.Employee;
import com.hierarchy.hierarchy.exception.EmployeeAlreadyExistsException;
import com.hierarchy.hierarchy.repository.EmployeeRepository;
import com.hierarchy.hierarchy.security.SecurityPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        String name = employee.getName();

        if (employeeRepository.existsByName(name)) {
            System.out.println("Employee already exists: " + employee.getName());

            throw new EmployeeAlreadyExistsException("Employee already exists: " + employee.getName());
        }

        int passwordStrength = SecurityPassword.calculatePasswordStrength(employee.getPassword());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setForce(String.valueOf(passwordStrength));

        return employeeRepository.save(employee);
    }
}
