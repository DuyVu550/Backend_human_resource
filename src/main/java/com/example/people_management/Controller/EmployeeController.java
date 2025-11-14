package com.example.people_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.people_management.Entity.Employees;
import com.example.people_management.dto.request.EmployeeCreationRequest;
import com.example.people_management.dto.response.ApiRespone;
import com.example.people_management.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    List<Employees> list;

    @PostMapping()
    ApiRespone<Employees> createEmployee(@RequestBody EmployeeCreationRequest employeeCreationRequest) {
        ApiRespone<Employees> apiResponse = new ApiRespone<>();
        apiResponse.setResult(employeeService.createEmployee(employeeCreationRequest));
        return apiResponse;
    }

    @GetMapping()
    List<Employees> displayAll() {
        return employeeService.displayAll();
    }

    @GetMapping("/{name}")
    ApiRespone<Employees> findEmployeesByName(@PathVariable("name") String name) {
        ApiRespone<Employees> apiRespone = new ApiRespone<>();
        apiRespone.setResult(employeeService.findEmployeesByName(name));
        return apiRespone;
    }

    @PutMapping("/{employ_id}")
    ApiRespone<Employees> updateEmployeesById(@PathVariable("employ_id") long id,
            @RequestBody EmployeeCreationRequest employeeCreationRequest) {
        ApiRespone<Employees> apiRespone = new ApiRespone<>();
        apiRespone.setResult(employeeService.updateEmployeesById(id, employeeCreationRequest));
        return apiRespone;
    }

    @DeleteMapping("/{employ_id}")
    String deleteEmployeesById(@PathVariable("employ_id") long id) {
        employeeService.DeleteEmployeesById(id);
        return "Delete Successfully";
    }

}
