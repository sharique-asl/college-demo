package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getCoursesByIds(@NotNull List<Long> ids) {
        if (ids.isEmpty())
            return courseRepository.findAll();

        return courseRepository.findAllById(ids);
    }

    @Override
    public Course createCourse(@Valid Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(@NotNull @Min(1) Long id, @Valid Course course) {
        if (courseRepository.existsById(id)) {
            course.setCourseId(id);
            return courseRepository.save(course);
        } else {
            // Handle the case where the course with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    @Override
    public Boolean deleteCourse(@NotNull @Min(1) Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if(course!=null){
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
