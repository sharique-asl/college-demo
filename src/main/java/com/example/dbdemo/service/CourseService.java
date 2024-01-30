package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id);

    List<Course> getCoursesByIds(List<Long> ids);

    Course createCourse(Course course);

    Course updateCourse(Course course);

    Boolean deleteCourse(Long id);
}
