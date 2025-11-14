package com.example.people_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.people_management.Entity.Employees;
import com.example.people_management.dto.request.EmployeeCreationRequest;
import com.example.people_management.repository.EmployeeRepository;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  public Employees createEmployee(EmployeeCreationRequest employeeCreationRequest) {
    Employees employees = new Employees();
    employees.setName(employeeCreationRequest.getName());
    employees.setAge(employeeCreationRequest.getAge());
    employees.setAddress(employeeCreationRequest.getAddress());
    employees.setDepartments_id(employeeCreationRequest.getDepartments_id());
    employees.setAvatar(employeeCreationRequest.getAvatar());
    employees.setSalary_rate(employeeCreationRequest.getSalary_rate());
    return employeeRepository.save(employees);
  }

  public Employees findEmployeesByName(String name) {
    return employeeRepository.findByName(name).orElseThrow(() -> new RuntimeException("No Found Employee"));
  }

  public Employees findEmployeesById(long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("No Found"));
  }

  public List<Employees> displayAll() {
    return employeeRepository.findAll();
  }

  public Employees updateEmployeesById(long id, EmployeeCreationRequest employeeCreationRequest) {
    Employees employees = findEmployeesById(id);
    employees.setName(employeeCreationRequest.getName());
    employees.setAge(employeeCreationRequest.getAge());
    employees.setAddress(employeeCreationRequest.getAddress());
    employees.setDepartments_id(employeeCreationRequest.getDepartments_id());
    employees.setAvatar(employeeCreationRequest.getAvatar());
    employees.setSalary_rate(employeeCreationRequest.getSalary_rate());
    return employeeRepository.save(employees);
  }

  public void DeleteEmployeesById(long id) {
    employeeRepository.deleteById(id);
  }

}
