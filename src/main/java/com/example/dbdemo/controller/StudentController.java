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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/students")
    public ResponseEntity<ResponseDTOWrapper> getAllStudents(
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

            List<StudentResponseDTO> studentsDTO = students
                    .stream()
                    .map(student -> StudentResponseDTO.generateStudentResponseDTO(student))
                    .collect(Collectors.toList());


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.builder()
                                .students(studentsDTO)
                                .build()
                    );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.builder()
                                    .errorMessage("Fetching students was unsuccessful")
                                    .build()
                    );
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            System.out.println(id);
            Student student = studentServiceImpl.getStudentById(id);

            StudentResponseDTO studentDTO = StudentResponseDTO.generateStudentResponseDTO(student);

            List<StudentResponseDTO> studentDTOs = new ArrayList<>(Arrays.asList(studentDTO));
            log.info(student.toString());

            if (student == null) {

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                ResponseDTOWrapper.builder()
                                        .errorMessage("No student found for the corresponding ID")
                                        .build()
                        );
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.builder()
                                    .students(studentDTOs)
                                    .build()
                    );

        } catch(Exception e) {

            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ResponseDTOWrapper.builder()
                                .errorMessage("Unable to fetch student using ID")
                                .build()
                );
        }
    }

    @GetMapping("/students/ids")
    public ResponseEntity<ResponseDTOWrapper> getStudentsByIds(@RequestParam List<Long> id) {
        try {
            List<Student> students = studentService.getStudentsByIds(id);

            List<StudentResponseDTO> studentsDTOs = students
                    .stream()
                    .map(student -> StudentResponseDTO.generateStudentResponseDTO(student))
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.builder()
                                    .students(studentsDTOs)
                                    .build()
                    );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.builder()
                                    .errorMessage("Unable to fetch students using IDs")
                                    .build()
                    );
        }
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudent(@Valid @RequestBody CreateStudentRequestDTO student) {
        try {
            Student createdStudent = studentService.createStudent(student.generateStudent());

            StudentResponseDTO createdStudentDTO = StudentResponseDTO.generateStudentResponseDTO(createdStudent);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Student created with id:" + createdStudentDTO.getId());
        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create student");
        }
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDTO student) {
        try{
            Student updatedStudent = studentService.updateStudent(id, student.generateStudent());

            if (updatedStudent == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Student to be updated not found");
            }

            StudentResponseDTO updatedStudentDTO = StudentResponseDTO.generateStudentResponseDTO(updatedStudent);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Student updated with id:" + updatedStudentDTO.getId());

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
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Deletion successful");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Deletion unsuccessful");
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete entry");
        }
    }
}
