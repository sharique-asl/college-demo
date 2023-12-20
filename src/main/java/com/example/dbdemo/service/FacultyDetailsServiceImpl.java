package com.example.dbdemo.service;

import com.example.dbdemo.model.FacultyDetails;
import com.example.dbdemo.repository.FacultyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyDetailsServiceImpl implements FacultyDetailsService {

    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;

    @Override
    public List<FacultyDetails> getAllFacultyDetails() {
        return facultyDetailsRepository.findAll();
    }

    @Override
    public FacultyDetails getFacultyDetailsById(Long id) {
        return facultyDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public FacultyDetails createFacultyDetails(FacultyDetails facultyDetails) {
        return facultyDetailsRepository.save(facultyDetails);
    }

    @Override
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

    @Override
    public void deleteFacultyDetails(Long id) {
        facultyDetailsRepository.deleteById(id);
    }
}
