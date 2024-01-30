package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDTO {
    private Long departmentId;
    private String departmentName;
    private String hod;
    public static DepartmentResponseDTO generateDepartmentResponseDTO(Department department) {

        if (department == null) {
            return DepartmentResponseDTO
                    .builder()
                    .build();
        }
        Long departmentId = department.getDepartmentId();
        String departmentName = department.getDepartmentName();
        String hod = department.getHod();

        return DepartmentResponseDTO
                .builder()
                    .departmentId(departmentId)
                    .departmentName(departmentName)
                    .hod(hod)
                .build();
    }
}
