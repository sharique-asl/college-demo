package com.example.dbdemo.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class StudentResponseDTO {
    private Long id;
    private int rollNumber;
    private Set<Long> courseIds; // Assuming course IDs for many-to-many relationship
    // Additional fields if needed

    public StudentResponseDTO(Long id, int rollNumber, Set<Long> courseIds) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.courseIds = courseIds;
    }
}
