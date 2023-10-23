package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    Employee updateEmployee(Employee employee, String id);

    void deleteEmployee(String id);
}
