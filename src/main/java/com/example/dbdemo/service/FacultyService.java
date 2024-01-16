package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculties();

    Faculty getFacultyById(Long id);

    List<Faculty> getFacultiesByIds(List<Long> ids);

    Faculty createFaculty(Faculty faculty);

    Faculty updateFaculty(Long id, Faculty faculty);

    Boolean deleteFaculty(Long id);
}
