package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Department;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Department Name should not be null or empty")
    @Size(max = 20, message = "Department Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Department Name should contain only letters.")
    private String departmentName;

    @NotBlank(message = "HOD Name should not be null or empty")
    @Size(max = 255, message = "HOD Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "HOD Name should contain only letters.")
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
