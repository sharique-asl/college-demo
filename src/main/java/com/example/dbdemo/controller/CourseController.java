package com.example.dbdemo.controller;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.service.CourseService;
import com.example.dbdemo.dto.request.ResponseDTOWrapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/getAllCourses", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/getCoursesByIds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<Course>> getCoursesByIds(@NotNull @RequestParam List<Long> id) {
        List<Course> courses = courseService.getCoursesByIds(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(courses)
                                .build()
                );
    }

    @PostMapping(value = "/createCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCourse(@Valid @RequestBody Course course) {

        try {
            Course createdCourse = courseService.createCourse(course);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Success");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failure");
        }
    }

    @PutMapping(value = "/updateCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<Course>> updateCourse(@Valid @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(course);

        return ResponseEntity
                .status(updatedCourse != null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(
                        ResponseDTOWrapper.<Course>builder()
                                .items(updatedCourse != null ? Collections.singletonList(updatedCourse) : null)
                                .errorMessage(updatedCourse == null ? "Course to be updated not found" : null)
                                .build()
                );
    }

    @DeleteMapping(value = "/deleteCourse/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<String>> deleteCourse(@NotNull @Min(1) @PathVariable Long id) {
        Boolean status = courseService.deleteCourse(id);

        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(
                        ResponseDTOWrapper.<String>builder()
                                .items(Collections.singletonList(status ? "Deletion successful" : "Deletion Failed"))
                                .build()
                );
    }
}
