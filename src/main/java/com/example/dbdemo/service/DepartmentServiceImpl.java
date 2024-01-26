package com.example.dbdemo.service;

import com.example.dbdemo.model.Department;
import com.example.dbdemo.repository.DepartmentRepository;
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
    public List<Department> getDepartmentsByIds(List<Long> ids) {

        if (ids.isEmpty())
            return departmentRepository.findAll();

        return departmentRepository.findAllById(ids);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        Long id = department.getDepartmentId();
        if (departmentRepository.existsById(id)) {
            department.setDepartmentId(id);
            return departmentRepository.save(department);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        Department dept = departmentRepository.findById(id).orElse(null);
        if(dept !=null){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
