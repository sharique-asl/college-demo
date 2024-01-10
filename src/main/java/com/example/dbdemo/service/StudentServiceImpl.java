package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public List<Student> getStudentsByIds(@NotNull List<Long> ids) {
        if (ids.isEmpty())
            return studentRepository.findAll();

        return studentRepository.findAllById(ids);
    }

    @Override
    public Student createStudent(@Valid Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(@NotNull @Min(1) Long id, @Valid Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        } else {
            // Handle the case where the student with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    @Override
    public void deleteStudent(@NotNull @Min(1) Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student!=null){
            student.setActive(false);
            studentRepository.save(student);
        }

//        studentRepository.deleteById(id);
    }
}
/*
* instead of downloading and installing, install docker desktop and then run
* > docker pull redis
* docker run --name redis-cache -p 6379:6379 -d redis:7.2.3
* */
//soft delete , fetch only active