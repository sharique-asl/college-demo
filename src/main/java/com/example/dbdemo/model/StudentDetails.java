package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student_details")
public class StudentDetails {

    // @Column(name = "student_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String fatherName;
    private String motherName;
    private String contactNumber;
    private String backupContactNumber; // can be fathers contact or pg contact number
    // back up can be for pg or local guardian if the student is older
    private String father_ContactNumber;
    private String dateOfBirth;
    private String email;


    @Enumerated(EnumType.STRING)
    private Gender gender; // enum
    private String aadharNumber;
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Student student;
}
// run the application and check if its running & table are being created or not.
// find how to generate your custom ID generators example => SC-PH, SC-CH, SC-BIO, SC-MBIO
// who adds what. Department -> admin, Courses -> admin, Faculty -> Admin, Student -> Admin
// admin will take care of every addition in the current scenario
