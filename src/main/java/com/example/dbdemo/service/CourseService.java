package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    List<Course> getCoursesById(List<Long> ids);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    void deleteCourse(Long id);
}
