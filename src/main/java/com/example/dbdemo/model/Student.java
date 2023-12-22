package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // because we want a sequential number for the Ids
    private Long id;
    private int rollNumber;

//    @JoinColumn(name = "student_details_id", referencedColumnName = "id")
//    private StudentDetails studentDetails;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}
