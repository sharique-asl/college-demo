package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // because we want a sequential number for the Ids
    private Long id;

    private int rollNumber;
    private String courseId;
    private Date courseJoinDate;

}
