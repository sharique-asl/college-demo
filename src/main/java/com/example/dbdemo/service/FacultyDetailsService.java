package com.example.dbdemo.service;

import com.example.dbdemo.model.FacultyDetails;

import java.util.List;

public interface FacultyDetailsService {

    List<FacultyDetails> getAllFacultyDetails();

    FacultyDetails getFacultyDetailsById(Long id);

    FacultyDetails createFacultyDetails(FacultyDetails facultyDetails);

    FacultyDetails updateFacultyDetails(Long id, FacultyDetails facultyDetails);

    void deleteFacultyDetails(Long id);
}
