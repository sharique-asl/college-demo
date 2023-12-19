package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long departmentID;

    private List<Long> courses;
    // 4 courses present, phy, chem, bio, micro-bio

    @OneToOne
    @JoinColumn(name = "faculty_details_id")
    private FacultyDetails facultyDetails;
}

