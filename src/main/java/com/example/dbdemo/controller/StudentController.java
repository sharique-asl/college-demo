package com.example.dbdemo.controller;

import com.example.dbdemo.dto.request.CreateStudentRequestDTO;
import com.example.dbdemo.dto.request.ResponseDTOWrapper;
import com.example.dbdemo.dto.request.UpdateStudentRequestDTO;
import com.example.dbdemo.dto.response.StudentResponseDTO;
import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.StudentService;
import com.example.dbdemo.service.StudentServiceImpl;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @GetMapping("/students/get/")
    public ResponseEntity<ResponseDTOWrapper<StudentResponseDTO>> getAllStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        try {
            List<Student> students = null;

            if (sort != null && !sort.isEmpty()) {
                students = studentServiceImpl.getAllSortedStudents(sort);
            } else {
                students = studentService.getAllStudents();
            }

            if (name != null || gender != null) {
                students = studentServiceImpl.getFilteredStudents(name, gender);
            }

            List<StudentResponseDTO> studentsDTO = students
                    .stream()
                    .map(StudentResponseDTO::generateStudentResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<StudentResponseDTO>builder()
                                    .items(studentsDTO)
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<StudentResponseDTO>builder()
                                    .errorMessage("Fetching students was unsuccessful")
                                    .build()
                    );
        }
    }

    @GetMapping("/students/get/ids")
    public ResponseEntity<ResponseDTOWrapper<StudentResponseDTO>> getStudentsByIds(@RequestParam List<Long> id) {
        try {
            List<Student> students = studentService.getStudentsByIds(id);

            List<StudentResponseDTO> studentsDTOs = students
                    .stream()
                    .map(StudentResponseDTO::generateStudentResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<StudentResponseDTO>builder()
                                    .items(studentsDTOs)
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<StudentResponseDTO>builder()
                                    .errorMessage("Unable to fetch students using IDs")
                                    .build()
                    );
        }
    }

    @PostMapping("/students/add")
    public ResponseEntity<ResponseDTOWrapper<String>> createStudent(@Valid @RequestBody CreateStudentRequestDTO student) {
        try {
            Student createdStudent = studentService.createStudent(student.generateStudent());

            StudentResponseDTO createdStudentDTO = StudentResponseDTO.generateStudentResponseDTO(createdStudent);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList("Student created with id:" + createdStudentDTO.getId()))
                                    .build()
                    );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .errorMessage("Unable to create student")
                                    .build()
                    );
        }
    }

    @PutMapping("/students/edit/{id}")
    public ResponseEntity<ResponseDTOWrapper<String>> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDTO student) {
        try {
            Student updatedStudent = studentService.updateStudent(id, student.generateStudent());

            if (updatedStudent == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                ResponseDTOWrapper.<String>builder()
                                        .errorMessage("Student to be updated not found")
                                        .build()
                        );
            }

            StudentResponseDTO updatedStudentDTO = StudentResponseDTO.generateStudentResponseDTO(updatedStudent);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList("Student updated with id:" + updatedStudentDTO.getId()))
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .errorMessage("Unable to update student")
                                    .build()
                    );
        }
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<ResponseDTOWrapper<String>> deleteStudent(@PathVariable Long id) {
        try {
            Boolean status = studentService.deleteStudent(id);

            return ResponseEntity
                    .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList(status ? "Deletion successful" : "Deletion unsuccessful"))
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .errorMessage("Unable to delete entry")
                                    .build()
                    );
        }
    }
}

