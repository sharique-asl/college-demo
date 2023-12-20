package com.example.dbdemo.controller;

import com.example.dbdemo.model.*;
import com.example.dbdemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<String> welcomeAdminMessage() {
        String message = "Welcome to the Admin Panel!";
        return ResponseEntity.ok(message);
    }

    // Student CRUD operations
    @Autowired
    private StudentService studentService;

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

    @PostMapping("/students")
//    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//        Student createdStudent = studentService.createStudent(student);
//        return ResponseEntity.ok(createdStudent);
//    }
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        //
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

    // Faculty CRUD operations
    @Autowired
    private FacultyService facultyService;
    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        return faculty != null ? ResponseEntity.ok(faculty) : ResponseEntity.notFound().build();
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

    // StudentDetails CRUD operations
    @Autowired
    private StudentDetailsService studentDetailsService;

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

    @PostMapping("/studentDetails")
    public ResponseEntity<StudentDetails> createStudentDetails(@RequestBody StudentDetails studentDetails) {
        //
        StudentDetails createdStudentDetails = studentDetailsService.createStudentDetails(studentDetails);
        return new ResponseEntity<>(createdStudentDetails, HttpStatus.CREATED);
    }

    @PutMapping("/studentDetails/{id}")
    public ResponseEntity<StudentDetails> updateStudentDetails(@PathVariable Long id,
                                                               @RequestBody StudentDetails studentDetails) {
        StudentDetails updatedStudentDetails = studentDetailsService.updateStudentDetails(id, studentDetails);
        return updatedStudentDetails != null ? ResponseEntity.ok(updatedStudentDetails) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/studentDetails/{id}")
    public ResponseEntity<Void> deleteStudentDetails(@PathVariable Long id) {
        studentDetailsService.deleteStudentDetails(id);
        return ResponseEntity.noContent().build();
    }

    // FacultyDetails CRUD operations
    @Autowired
    private FacultyDetailsService facultyDetailsService;

    @GetMapping("/facultyDetails")
    public ResponseEntity<List<FacultyDetails>> getAllFacultyDetails() {
        List<FacultyDetails> facultyDetailsList = facultyDetailsService.getAllFacultyDetails();
        return ResponseEntity.ok(facultyDetailsList);
    }

    @GetMapping("/facultyDetails/{id}")
    public ResponseEntity<FacultyDetails> getFacultyDetailsById(@PathVariable Long id) {
        FacultyDetails facultyDetails = facultyDetailsService.getFacultyDetailsById(id);
        return facultyDetails != null ? ResponseEntity.ok(facultyDetails) : ResponseEntity.notFound().build();
    }

    @PostMapping("/facultyDetails")
    public ResponseEntity<FacultyDetails> createFacultyDetails(@RequestBody FacultyDetails facultyDetails) {
        FacultyDetails createdFacultyDetails = facultyDetailsService.createFacultyDetails(facultyDetails);
        return new ResponseEntity<>(createdFacultyDetails, HttpStatus.CREATED);
    }

    @PutMapping("/facultyDetails/{id}")
    public ResponseEntity<FacultyDetails> updateFacultyDetails(@PathVariable Long id,
                                                               @RequestBody FacultyDetails facultyDetails) {
        FacultyDetails updatedFacultyDetails = facultyDetailsService.updateFacultyDetails(id, facultyDetails);
        return updatedFacultyDetails != null ? ResponseEntity.ok(updatedFacultyDetails) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/facultyDetails/{id}")
    public ResponseEntity<Void> deleteFacultyDetails(@PathVariable Long id) {
        facultyDetailsService.deleteFacultyDetails(id);
        return ResponseEntity.noContent().build();
    }

    // Course CRUD operations
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Department CRUD operations
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department createdDepartment = departmentService.createDepartment(department);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return updatedDepartment != null ? ResponseEntity.ok(updatedDepartment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
