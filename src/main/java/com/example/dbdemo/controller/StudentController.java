package com.example.dbdemo.controller;

import com.example.dbdemo.dto.response.StudentResponseDTO;
import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.StudentService;
import com.example.dbdemo.service.StudentServiceImpl;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class StudentController {
    // Student CRUD operations
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        try{
            List<Student> students = null;

            if (sort != null && !sort.isEmpty()) {
                students = studentServiceImpl.getAllSortedStudents(sort);
            /*
                /students?sort=name: Get all students sorted by name.
                /students?sort=name,desc: Get all students sorted by name in descending order.
             */
            } else {
                students = studentService.getAllStudents();
            }

            if (name != null || gender != null) {
                students = studentServiceImpl.getFilteredStudents(name, gender);
                System.out.println(students);
            }

//            List<StudentResponseDTO> studentsDTO = students.stream().map(student -> modelMapper.map(student, StudentResponseDTO.class)).collect(Collectors.toList());
            List<StudentResponseDTO> studentsDTO = students
                    .stream()
                    .map(student -> StudentResponseDTO.generateStudentResponseDTO(student))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(studentsDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fetching Students was unsuccessful");
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.getStudentById(id);
            log.info(student.toString());

            if (student == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No student found for the corresponding ID");
            }
            return ResponseEntity
                    .ok(StudentResponseDTO.generateStudentResponseDTO(student));
        } catch(Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to fetch student using ID");
        }
    }

    @GetMapping("/students/ids")
    public ResponseEntity<?> getStudentsByIds(@RequestParam List<Long> id) {
        try {
            List<Student> students = studentService.getStudentsByIds(id);

            List<StudentResponseDTO> studentsDTOs = students
                    .stream()
                    .map(student -> StudentResponseDTO.generateStudentResponseDTO(student))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(studentsDTOs);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Unable to fetch students using IDs");
        }
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);

            StudentResponseDTO createdStudentDTO = StudentResponseDTO.generateStudentResponseDTO(createdStudent);
            return new ResponseEntity<>(createdStudentDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create student");
        }
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        try{
            Student updatedStudent = studentService.updateStudent(id, student);

            if (updatedStudent == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Student to be updated not found");
            }

            StudentResponseDTO updatedStudentDTO = StudentResponseDTO.generateStudentResponseDTO(updatedStudent);
            return ResponseEntity.ok(updatedStudentDTO);

        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to update student");
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try{
            Boolean status = studentService.deleteStudent(id);

            if (status)
                return ResponseEntity.status(200).body("Deletion successful");
            return ResponseEntity.status(500).body("Deletion unsuccessful");
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete entry");
        }
    }
}
