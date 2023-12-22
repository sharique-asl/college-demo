package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long departmentId;
    private String departmentName;
    private String hod;

    @OneToMany(mappedBy = "department")
    private Set<Course> courses;

    @OneToMany(mappedBy = "department")
    private Set<Faculty> faculty;

}
