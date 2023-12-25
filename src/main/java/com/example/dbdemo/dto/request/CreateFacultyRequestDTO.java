package com.example.dbdemo.dto.request;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
public class CreateFacultyRequestDTO {
    private Department department;
    private Set<Course> courses;
}
