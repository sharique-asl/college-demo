package com.example.dbdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long departmentId;

    private String departmentName;


}
