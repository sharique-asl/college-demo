package com.example.dbdemo.service;

import com.example.dbdemo.model.Course;
import com.example.dbdemo.repository.CourseRepository;
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
    public List<Course> getCoursesByIds(List<Long> ids) {
        if (ids.isEmpty())
            return courseRepository.findAll();

        return courseRepository.findAllById(ids);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Long id = course.getCourseId();
        if (courseRepository.existsById(id)) {
            course.setCourseId(id);
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if(course!=null){
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
