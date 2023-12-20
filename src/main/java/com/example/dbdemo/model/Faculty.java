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
//should we join? many_to_many -> separate table , 1 table per - course,faculty
    private List<Long> courses;
    // 4 courses present, phy, chem, bio, micro-bio

<<<<<<< Updated upstream
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private FacultyDetails facultyDetails;
=======

//     no join needed to faculty_details
>>>>>>> Stashed changes
}

