package com.example.people_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.people_management.Entity.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findByName(String name);

}
