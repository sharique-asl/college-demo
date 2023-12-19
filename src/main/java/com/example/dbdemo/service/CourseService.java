package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        if (courseRepository.existsById(id)) {
            course.setCourseId(id);
            return courseRepository.save(course);
        } else {
            // Handle the case where the course with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
