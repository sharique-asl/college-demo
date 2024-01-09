package com.example.dbdemo.controller;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Slf4j
public class StudentController {
    // Student CRUD operations
    @Autowired
    private StudentService studentService;
    @Autowired
//    private StudentDetailsService studentDetailsService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

//    @GetMapping("/students/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//
//        Student student = studentService.getStudentById(id);
//        log.info(student.toString());
//
//        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
//    }
//change requestParam , not getMapping , diff bw getMapping & PostMapping
    @GetMapping("/students/ids")
    public ResponseEntity<List<Student>> getStudentsByIds(@RequestParam List<Long> id) {
        List<Student> students = studentService.getStudentsByIds(id);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


}