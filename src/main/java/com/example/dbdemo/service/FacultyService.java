package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;

import java.util.List;

public interface FacultyService {

    List<Faculty> getAllFaculties();

    List<Faculty> getFacultiesByIds(List<Long> ids);

    Faculty createFaculty(Faculty faculty);

    Faculty updateFaculty(Long id, Faculty faculty);

    void deleteFaculty(Long id);
}
