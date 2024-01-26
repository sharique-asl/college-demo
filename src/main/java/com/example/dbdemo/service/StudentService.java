package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.utilities.Gender;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(String name, Gender gender, String sort);
    Student getStudentById(Long id);
    List<Student> getStudentsByIds(List<Long> ids);
    Student createStudent(Student student);
    Student updateStudent(Student student);
    Boolean deleteStudent(Long id);
}
