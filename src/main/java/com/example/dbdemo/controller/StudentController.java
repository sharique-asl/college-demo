package com.example.dbdemo.controller;

import com.example.dbdemo.dto.request.CreateStudentRequestDTO;
import com.example.dbdemo.dto.request.ResponseDTOWrapper;
import com.example.dbdemo.dto.request.UpdateStudentRequestDTO;
import com.example.dbdemo.dto.response.StudentResponseDTO;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.StudentService;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/getAllStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<StudentResponseDTO>> getAllStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        try {
            if(sort == null)
                sort = "id";
            List<Student> students = studentService.getAllStudents(name, gender, sort);

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

    @GetMapping(value = "/getStudentsByIds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<StudentResponseDTO>> getStudentsByIds(@NotNull @RequestParam List<Long> id) {
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

    @PostMapping(value = "/createStudent", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createStudent(@Valid @RequestBody CreateStudentRequestDTO student) {
        try {
            Student createdStudent = studentService.createStudent(student.generateStudent());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Success");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failure");
        }
    }

    @PutMapping(value = "/updateStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<String>> updateStudent(@Valid @RequestBody UpdateStudentRequestDTO student) {
        try {
            Student updatedStudent = studentService.updateStudent(student.generateStudent());

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

    @DeleteMapping(value = "/deleteStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<String>> deleteStudent(@NotNull @Min(1) @PathVariable Long id) {
        try {
            Boolean status = studentService.deleteStudent(id);

            return ResponseEntity
                    .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList(status ? "Deletion successful" : "Deletion Failed"))
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

