package com.example.dbdemo.service;

import com.example.dbdemo.model.StudentDetails;

import java.util.List;

public interface StudentDetailsService {
    List<StudentDetails> getAllStudentDetails();
    StudentDetails getStudentDetailsById(Long id);
    StudentDetails createStudentDetails(StudentDetails studentDetails);
    StudentDetails updateStudentDetails(Long id, StudentDetails studentDetails);
    void deleteStudentDetails(Long id);
}
