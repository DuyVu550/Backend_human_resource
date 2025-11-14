package com.example.people_management.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long employ_id;
    String name;
    int age;
    String address;
    int departments_id;
    String avatar;
    double salary_rate;
}
