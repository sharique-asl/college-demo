package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long rollNumber;

    private String name;

    private String fatherName;

    private String motherName;

    private String contactNumber;

    private String backupContactNumber;

    private String fatherContactNumber;

    private Date dateOfBirth;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String aadharNumber;

    private String pan;

    @Builder.Default
    private boolean isActive = true;

}
