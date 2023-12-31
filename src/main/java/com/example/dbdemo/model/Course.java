package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "course")
public class Course  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;
    private String courseName;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

//    @ManyToMany(mappedBy = "courses")
//    private Set<Student> students;

//    @ManyToOne
//    @JoinColumn(name = "faculty_id")
//    private Faculty faculty;
}
//coursename,department -mandatory , not null -> no special ->