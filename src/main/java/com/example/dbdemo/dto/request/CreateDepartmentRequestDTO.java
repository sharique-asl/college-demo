package com.example.dbdemo.dto.request;

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
public class CreateDepartmentRequestDTO {
    private Long departmentId;

    @NotBlank(message = "Department Name should not be null or empty")
    @Size(max = 20, message = "Department Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Department Name should contain only letters.")
    private String departmentName;

    @NotBlank(message = "HOD Name should not be null or empty")
    @Size(max = 255, message = "HOD Name should not exceed 20 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "HOD Name should contain only letters.")
    private String hod;

    public Department generateDepartment() {
        Long departmentId = this.getDepartmentId();
        String departmentName = this.getDepartmentName();
        String hod = this.getHod();

        return Department
                .builder()
                    .departmentId(departmentId)
                    .departmentName(departmentName)
                    .hod(hod)
                .build();
    }
}
