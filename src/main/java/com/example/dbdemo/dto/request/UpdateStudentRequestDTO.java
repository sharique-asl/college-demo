package com.example.dbdemo.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateStudentRequestDTO {
    private int rollNumber;
    private Set<Long> courseIds; // Assuming course IDs for many-to-many relationship
    // Additional fields if needed
}
