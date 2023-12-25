package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class FacultyResponseDTO {
    private Long id;
    private Department department;
    private Set<Course> courses;
}
