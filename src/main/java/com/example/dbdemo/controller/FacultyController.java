package com.example.dbdemo.controller;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class FacultyController {
    // Faculty CRUD operations
    @Autowired
    private FacultyService facultyService;
    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

//    @GetMapping("/faculties/{id}")
//    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
//        Faculty faculty = facultyService.getFacultyById(id);
//        return faculty != null ? ResponseEntity.ok(faculty) : ResponseEntity.notFound().build();
//    }

    @PostMapping("/faculties")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/faculties/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(id, faculty);
        return updatedFaculty != null ? ResponseEntity.ok(updatedFaculty) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

}
