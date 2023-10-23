package com.example.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        // Optional<Employee> employee = employeeRepository.findById(id);
        // if(employee.isPresent()){
        // return employee.get();
        // }
        // throw new EmployeeNotFoundException("Employee", "id", id);

        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, String id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(String id) {
        var existingId = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee", "Id", id)).getId();

        employeeRepository.deleteById(existingId);
    }

}
