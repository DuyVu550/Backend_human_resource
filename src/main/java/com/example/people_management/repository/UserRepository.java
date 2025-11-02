package com.example.people_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.people_management.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
