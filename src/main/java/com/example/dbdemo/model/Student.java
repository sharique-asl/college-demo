package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="student")
public class Student {

    //    @GenericGenerator()
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // because we want a sequential number for the Ids
    private Long id;

    private String studentName;
    private String studentClass;
    private String rollNumber;
    // which course taken
    // when joined


    @OneToOne
    @JoinColumn(name = "student_details_id")
    private StudentDetails studentDetails;
}
