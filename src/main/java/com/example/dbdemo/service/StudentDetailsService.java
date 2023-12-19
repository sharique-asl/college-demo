package com.example.dbdemo.service;

import com.example.dbdemo.model.StudentDetails;
import com.example.dbdemo.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDetailsService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    public List<StudentDetails> getAllStudentDetails() {
        return studentDetailsRepository.findAll();
    }

    public StudentDetails getStudentDetailsById(Long id) {
        return studentDetailsRepository.findById(id).orElse(null);
    }

    public StudentDetails createStudentDetails(StudentDetails studentDetails) {
        return studentDetailsRepository.save(studentDetails);
    }

    public StudentDetails updateStudentDetails(Long id, StudentDetails studentDetails) {
        if (studentDetailsRepository.existsById(id)) {
            studentDetails.setId(id);
            return studentDetailsRepository.save(studentDetails);
        } else {
            // Handle the case where the student details with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public void deleteStudentDetails(Long id) {
        studentDetailsRepository.deleteById(id);
    }
}
