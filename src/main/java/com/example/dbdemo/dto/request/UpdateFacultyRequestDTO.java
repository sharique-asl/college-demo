package com.example.dbdemo.dto.request;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.model.Department;

import java.util.Set;

public class UpdateFacultyRequestDTO {
    private Department department;
    private Set<Course> courses;
}
