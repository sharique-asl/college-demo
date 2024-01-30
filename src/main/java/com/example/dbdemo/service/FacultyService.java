package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.utilities.Gender;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculties(String name, Gender gender, String sort);

    Faculty getFacultyById(Long id);

    List<Faculty> getFacultiesByIds(List<Long> ids);

    Faculty createFaculty(Faculty faculty);

    Faculty updateFaculty(Faculty faculty);

    Boolean deleteFaculty(Long id);
}
