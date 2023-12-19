package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;

    @Column
    private String courseName;
    private String assignedFacultyName;

    private Long departmentID;

}