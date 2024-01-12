package com.example.dbdemo.controller;

import com.example.dbdemo.dto.response.StudentResponseDTO;
import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.service.StudentService;
import com.example.dbdemo.service.StudentServiceImpl;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        List<Student> students = null ;

        if (sort != null && !sort.isEmpty()) {
            students = studentServiceImpl.getAllSortedStudents(sort);
            /*
                /students?sort=name: Get all students sorted by name.
                /students?sort=name,desc: Get all students sorted by name in descending order.
             */
        }
        else {
            students = studentService.getAllStudents();
        }

        if (name != null || gender !=null) {
            students = studentServiceImpl.getFilteredStudents(name, gender);
            System.out.println(students);
        }

        List<StudentResponseDTO> studentsDTO = students.stream().map(student -> modelMapper.map(student, StudentResponseDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(studentsDTO);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        Student student = studentService.getStudentById(id);
        log.info(student.toString());

        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

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
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean status = studentService.deleteStudent(id);

        if (status)
            return ResponseEntity.status(200).body("Deletion successful");
        return ResponseEntity.status(500).body("Deletion unsuccessful");
    }
}
