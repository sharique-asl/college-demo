package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.repository.StudentRepository;
import com.example.dbdemo.utilities.FilterUtils;
import com.example.dbdemo.utilities.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private FilterUtils<Student> filterUtil = new FilterUtils<>();

    @Override
    public List<Student> getAllStudents(String name, Gender gender,String sort) {
        List<Student> students = this.filterUtil.filterList(studentRepository.findAll(), Student::isActive);
        if (sort != null && !sort.isEmpty()) {
            students = filterUtil.filterList(studentRepository.findAll(Sort.by(sort)), Student::isActive);
        }
        if (name != null || gender != null) {
            students = studentRepository.findFilteredStudents(name, gender);
        }
        return students;
    }

    @Override
//    @Cacheable(value = "student", key = "#id")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getStudentsByIds(List<Long> ids) {
        if (ids.isEmpty())
            return this.filterUtil.filterList(studentRepository.findAll(), Student::isActive);

        return this.filterUtil.filterList(studentRepository.findAllById(ids), Student::isActive);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Long id = student.getId();
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student!=null){
            student.setActive(false);
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}
