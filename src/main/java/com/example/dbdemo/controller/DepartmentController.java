package com.example.dbdemo.controller;

import com.example.dbdemo.model.Department;
import com.example.dbdemo.service.DepartmentService;
import com.example.dbdemo.dto.request.ResponseDTOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;
import java.util.List;

@RestController
public class DepartmentController {
    // Department CRUD operations
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments/get")
    public ResponseEntity<ResponseDTOWrapper<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseDTOWrapper.<Department>builder()
                                .items(departments)
                                .build()
                );
    }

    @GetMapping("/departments/get/ids")
    public ResponseEntity<ResponseDTOWrapper<Department>> getDepartmentsByIds(@RequestParam List<Long> id) {
        List<Department> departments = departmentService.getDepartmentsByIds(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseDTOWrapper.<Department>builder()
                                .items(departments)
                                .build()
                );
    }

    @PostMapping("/departments/add")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        try {
            Department createdDepartment = departmentService.createDepartment(department);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Success");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failure");
        }
    }

    @PutMapping("/departments/edit/{id}")
    public ResponseEntity<ResponseDTOWrapper<Department>> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);

        return ResponseEntity
                .status(updatedDepartment != null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(
                        ResponseDTOWrapper.<Department>builder()
                                .items(updatedDepartment != null ? Collections.singletonList(updatedDepartment) : null)
                                .errorMessage(updatedDepartment == null ? "Department to be updated not found" : null)
                                .build()
                );
    }

    @DeleteMapping("/departments/delete/{id}")
    public ResponseEntity<ResponseDTOWrapper<String>> deleteDepartment(@PathVariable Long id) {
        Boolean status = departmentService.deleteDepartment(id);

        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(
                        ResponseDTOWrapper.<String>builder()
                                .items(Collections.singletonList(status ? "Deletion successful" : "Deletion Failed"))
                                .build()
                );
    }
}
