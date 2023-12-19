package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        } else {
            // Handle the case where the student with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
