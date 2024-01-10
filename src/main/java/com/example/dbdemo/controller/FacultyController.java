package com.example.dbdemo.controller;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/faculties/ids")
    public ResponseEntity<List<Faculty>> getFacultiesByIds(@RequestParam List<Long> id) {
        List<Faculty> faculties = facultyService.getFacultiesByIds(id);
        faculties = faculties.stream().filter(facu -> facu.isActive())
                .collect(Collectors.toList());
        return ResponseEntity.ok(faculties);
    }

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
