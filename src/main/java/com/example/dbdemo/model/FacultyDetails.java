package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "faculty_details")
public class FacultyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String fatherName;
    private String motherName;
    private String contactNumber;
    private String backupContactNumber; // can be fathers contact or pg contact number
    // back up can be for pg or local guardian if the student is older
    private String maritalStatus;
    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender; // enum
    private String aadharNumber;
    private String panNumber;
    private boolean isActive;
    private Date dateOfJoining;
}
