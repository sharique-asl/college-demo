package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "faculty")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Faculty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String fatherName;

    private String motherName;

    private String contactNumber;

    private String backupContactNumber;

    private LocalDate dateOfBirth;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String aadharNumber;

    private String pan;

    private String maritalStatus;

    @PastOrPresent(message = "Date of joining should be in the past or present.")
    @Builder.Default
    private LocalDate dateOfJoining = LocalDate.now();

    @Builder.Default
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses;
}