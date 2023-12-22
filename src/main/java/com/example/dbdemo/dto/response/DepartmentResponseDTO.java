package com.example.dbdemo.dto.response;

import lombok.Data;

@Data
public class DepartmentResponseDTO {
    private Long departmentId;
    private String departmentName;
    private String hod;
    // Additional fields if needed

    public DepartmentResponseDTO(Long departmentId, String departmentName, String hod) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.hod = hod;
    }
}
