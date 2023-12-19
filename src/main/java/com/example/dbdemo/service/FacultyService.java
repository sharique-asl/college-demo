package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        } else {
            // Handle the case where the faculty with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
