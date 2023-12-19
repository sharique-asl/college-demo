package com.example.dbdemo.service;

import com.example.dbdemo.model.FacultyDetails;
import com.example.dbdemo.repository.FacultyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyDetailsService {

    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;

    public List<FacultyDetails> getAllFacultyDetails() {
        return facultyDetailsRepository.findAll();
    }

    public FacultyDetails getFacultyDetailsById(Long id) {
        return facultyDetailsRepository.findById(id).orElse(null);
    }

    public FacultyDetails createFacultyDetails(FacultyDetails facultyDetails) {
        return facultyDetailsRepository.save(facultyDetails);
    }

    public FacultyDetails updateFacultyDetails(Long id, FacultyDetails facultyDetails) {
        if (facultyDetailsRepository.existsById(id)) {
            facultyDetails.setId(id);
            return facultyDetailsRepository.save(facultyDetails);
        } else {
            // Handle the case where the faculty details with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public void deleteFacultyDetails(Long id) {
        facultyDetailsRepository.deleteById(id);
    }
}
