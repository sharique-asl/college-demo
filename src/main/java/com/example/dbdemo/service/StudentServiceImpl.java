package com.example.dbdemo.service;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.repository.StudentRepository;
import com.example.dbdemo.utilities.FilterUtils;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    public List<Student> getAllStudents() {
        return this.filterUtil.filterList(studentRepository.findAll(), Student::isActive);
    }

    public List<Student> getAllSortedStudents(String sort){
        return this.filterUtil.filterList(studentRepository.findAll(Sort.by(sort)), Student::isActive);
    }
    @Override
//    @Cacheable(value = "student", key = "#id")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getFilteredStudents(String name, Gender gender) {

        return studentRepository.findFilteredStudents(name, gender);

//        List<Student> students = studentRepository.findAll();
//
//        Predicate<Student> nameFilter = student -> name == null || student.getName().contains(name);
//        Predicate<Student> genderFilter = student -> gender == null || student.getGender().equals(gender);
//
//        Predicate<Student> combinedFilter = nameFilter.and(genderFilter);
//
//        return this.filterUtil.filterList(students, combinedFilter);
    }



    @Override
    public List<Student> getStudentsByIds(@NotNull List<Long> ids) {
        if (ids.isEmpty())
            return this.filterUtil.filterList(studentRepository.findAll(), Student::isActive);

        return this.filterUtil.filterList(studentRepository.findAllById(ids), Student::isActive);
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
    public Boolean deleteStudent(@NotNull @Min(1) Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student!=null){
            student.setActive(false);
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}
/*
* instead of downloading and installing, install docker desktop and then run
* > docker pull redis
* docker run --name redis-cache -p 6379:6379 -d redis:7.2.3
* */
//soft delete , fetch only active