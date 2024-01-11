package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    Boolean deleteCourse(Long id);

    List<Course> getCoursesByIds(List<Long> ids) ;
}
