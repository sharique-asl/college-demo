package com.example.dbdemo.controller;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.service.CourseService;
import com.example.dbdemo.dto.request.ResponseDTOWrapper;
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
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collections;
import java.util.List;

@RestController
public class CourseController {
    // Course CRUD operations
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses/get")
    public ResponseEntity<ResponseDTOWrapper<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(courses)
                                .build()
                );
    }

    @GetMapping("/courses/get/ids")
    public ResponseEntity<ResponseDTOWrapper<Course>> getCoursesByIds(@RequestParam List<Long> id) {
        List<Course> courses = courseService.getCoursesByIds(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(courses)
                                .build()
                );
    }

    @PostMapping("/courses/add")
    public ResponseEntity<ResponseDTOWrapper<Course>> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(Collections.singletonList(createdCourse))
                                .build()
                );
    }

    @PutMapping("courses/edit/{id}")
    public ResponseEntity<ResponseDTOWrapper<Course>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);

        return ResponseEntity
                .status(updatedCourse != null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(updatedCourse != null ? Collections.singletonList(updatedCourse) : null)
                                .errorMessage(updatedCourse == null ? "Course to be updated not found" : null)
                                .build()
                );
    }

    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<ResponseDTOWrapper<String>> deleteCourse(@PathVariable Long id) {
        Boolean status = courseService.deleteCourse(id);

        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(
                        ResponseDTOWrapper.<String>builder()
                                .items(Collections.singletonList(status ? "Deletion successful" : "Deletion unsuccessful"))
                                .build()
                );
    }
}
