package com.employee.employeemanagement.controller;

import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // CREATE
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // READ ALL
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // READ SINGLE
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmp) {
        Optional<Employee> empOptional = employeeRepository.findById(id);
        if (empOptional.isPresent()) {
            Employee emp = empOptional.get();
            emp.setFirstName(updatedEmp.getFirstName());
            emp.setLastName(updatedEmp.getLastName());
            emp.setEmail(updatedEmp.getEmail());
            emp.setContact(updatedEmp.getContact());
            emp.setGender(updatedEmp.getGender());
            emp.setLocation(updatedEmp.getLocation());
            emp.setDob(updatedEmp.getDob());
            return employeeRepository.save(emp);
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
