package com.example.dbdemo.service;

import com.example.dbdemo.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Long id);
    List<Department> getDepartmentsByIds(List<Long> ids);
    Department createDepartment(Department department);
    Department updateDepartment(Department department);
    Boolean deleteDepartment(Long id);
}
