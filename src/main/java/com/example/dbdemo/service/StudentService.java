package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
//    Student getStudentById(Long id);
    List<Student> getStudentsByIds(List<Long> ids);
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    Boolean deleteStudent(Long id);
}
