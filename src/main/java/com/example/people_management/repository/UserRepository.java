package com.example.people_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.people_management.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByRole(String role);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    void deleteByUsername(String name);

    Optional<User> findByRole(String role);
}
