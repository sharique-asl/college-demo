package com.example.dbdemo.controller;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.model.StudentDetails;
import com.example.dbdemo.service.StudentDetailsService;
import com.example.dbdemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class StudentController {
    // Student CRUD operations
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDetailsService studentDetailsService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }
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

    // StudentDetails CRUD operations

    @GetMapping("/studentDetails")
    public ResponseEntity<List<StudentDetails>> getAllStudentDetails() {
        List<StudentDetails> studentDetailsList = studentDetailsService.getAllStudentDetails();
        return ResponseEntity.ok(studentDetailsList);
    }

    @GetMapping("/studentDetails/{id}")
    public ResponseEntity<StudentDetails> getStudentDetailsById(@PathVariable Long id) {
        StudentDetails studentDetails = studentDetailsService.getStudentDetailsById(id);
        return studentDetails != null ? ResponseEntity.ok(studentDetails) : ResponseEntity.notFound().build();
    }

    @GetMapping("/studentDetails/ids")
    public ResponseEntity<List<StudentDetails>> getStudentDetailsByIds(@RequestParam List<Long> id) {
        List<StudentDetails> studentDetailsList = studentDetailsService.getStudentDetailsByIds(id);
        return ResponseEntity.ok(studentDetailsList);
    }

    @PostMapping("/studentDetails")
    public ResponseEntity<StudentDetails> createStudentDetails(@RequestBody StudentDetails studentDetails) {
        StudentDetails createdStudentDetails = studentDetailsService.createStudentDetails(studentDetails);
        return new ResponseEntity<>(createdStudentDetails, HttpStatus.CREATED);
    }

    @PutMapping("/studentDetails/{id}")
    public ResponseEntity<StudentDetails> updateStudentDetails(@PathVariable Long id, @RequestBody StudentDetails studentDetails) {
        StudentDetails updatedStudentDetails = studentDetailsService.updateStudentDetails(id, studentDetails);
        return updatedStudentDetails != null ? ResponseEntity.ok(updatedStudentDetails) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/studentDetails/{id}")
    public ResponseEntity<Void> deleteStudentDetails(@PathVariable Long id) {
        studentDetailsService.deleteStudentDetails(id);
        return ResponseEntity.noContent().build();
    }

}