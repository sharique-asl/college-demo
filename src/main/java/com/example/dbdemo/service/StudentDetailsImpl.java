package com.example.dbdemo.service;

import com.example.dbdemo.model.StudentDetails;
import com.example.dbdemo.repository.StudentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDetailsImpl implements StudentDetailsService {

    @Autowired
    private StudentDetailsRepository studentDetailsRepository;

    @Override
    public List<StudentDetails> getAllStudentDetails() {
        return studentDetailsRepository.findAll();
    }

    @Override
    public StudentDetails getStudentDetailsById(Long id) {
        return studentDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentDetails> getStudentDetailsByIds(List<Long> ids) {
        return studentDetailsRepository.findAllById(ids);
    }

    @Override
    public StudentDetails createStudentDetails(StudentDetails studentDetails) {
        return studentDetailsRepository.save(studentDetails);
    }

    @Override
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

    @Override
    public Boolean deleteStudentDetails(Long id) {
//        studentDetailsRepository.deleteById(id);
        StudentDetails obj = this.getStudentDetailsById(id);
        if (obj==null)
            return false;
        else {
            if(obj.isActive())
                return true;
            obj.setActive(false);
            return this.updateStudentDetails(id, obj) != null;
        }
    }
}
