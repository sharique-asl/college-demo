package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDTO {
    private Long id;
    private Long rollNumber;
    private String name;
    private String fatherName;
    private String motherName;
    private String contactNumber;
    private String backupContactNumber;
    private String fatherContactNumber;
    private Date dateOfBirth;
    private String email;
    private Gender gender;
    private String aadharNumber;
    private String pan;
    private boolean isActive = true;

    public static StudentResponseDTO generateStudentResponseDTO(Student student) {

        if (student == null) {
            return StudentResponseDTO
                    .builder()
                    .build();
        }
        Long id = student.getId();
        Long rollNumber = student.getRollNumber();
        String name = student.getName();
        String fatherName = student.getFatherName();
        String motherName = student.getMotherName();
        String contactNumber = student.getContactNumber();
        String backupContactNumber = student.getBackupContactNumber();
        String fatherContactNumber = student.getFatherContactNumber();
        Date dateOfBirth = student.getDateOfBirth();
        String email = student.getEmail();
        Gender gender = student.getGender();
        String aadharNumber = student.getAadharNumber();
        String pan = student.getPan();
        boolean isActive = student.isActive();

        return StudentResponseDTO
                .builder()
                    .id(id)
                    .rollNumber(rollNumber)
                    .name(name)
                    .fatherName(fatherName)
                    .motherName(motherName)
                    .contactNumber(contactNumber)
                    .backupContactNumber(backupContactNumber)
                    .fatherContactNumber(fatherContactNumber)
                    .dateOfBirth(dateOfBirth)
                    .email(email)
                    .gender(gender)
                    .aadharNumber(aadharNumber)
                    .pan(pan)
                    .isActive(isActive)
                .build();
    }

}
