package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.model.StudentDetails;

import java.util.List;

public interface StudentDetailsService {
    List<StudentDetails> getAllStudentDetails();
    StudentDetails getStudentDetailsById(Long id);
    List<StudentDetails> getStudentDetailsByIds(List<Long> ids);
    StudentDetails createStudentDetails(StudentDetails studentDetails);
    StudentDetails updateStudentDetails(Long id, StudentDetails studentDetails);
    Boolean deleteStudentDetails(Long id);
}
