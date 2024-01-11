package com.example.dbdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long departmentId;

    @NotBlank(message = "Department Name should not be null or empty")
    @Size(max = 20, message = "Department Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Department Name should contain only letters.")
    private String departmentName;

    @NotBlank(message = "HOD Name should not be null or empty")
    @Size(max = 255, message = "HOD Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "HOD Name should contain only letters.")
    private String hod;


    private boolean isActive = true;

//    @OneToMany(mappedBy = "department")
//    private Set<Course> courses;

//    @OneToMany(mappedBy = "department")
//    private Set<Faculty> faculty;

}
//departmentName , hod - mandatory , not null -> no special