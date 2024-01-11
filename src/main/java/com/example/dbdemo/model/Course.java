package com.example.dbdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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


    @NotBlank(message = "Course Name should not be null or empty")
    @Size(max = 10, message = "Course Name should not exceed 10 characters.")
    @Pattern(regexp = "^[0-9a-zA-Z ]*$", message = "Course Name should contain only letters and digits.")
    private String courseName;


    private boolean isActive = true;

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