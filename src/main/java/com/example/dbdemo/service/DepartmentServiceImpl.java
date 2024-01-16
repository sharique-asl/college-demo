package com.example.dbdemo.service;

import com.example.dbdemo.model.Department;
import com.example.dbdemo.repository.DepartmentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getDepartmentsByIds(@NotNull List<Long> ids) {

        if (ids.isEmpty())
            return departmentRepository.findAll();

        return departmentRepository.findAllById(ids);
    }

    @Override
    public Department createDepartment(@Valid Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(@NotNull @Min(1) Long id, @Valid Department department) {
        if (departmentRepository.existsById(id)) {
            department.setDepartmentId(id);
            return departmentRepository.save(department);
        } else {
            // Handle the case where the department with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    @Override
    public Boolean deleteDepartment(@NotNull @Min(1) Long id) {
        Department dept = departmentRepository.findById(id).orElse(null);
        if(dept !=null){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
