package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyResponseDTO {
    private Long id;
    private String name;
    private String fatherName;
    private String motherName;
    private String contactNumber;
    private String backupContactNumber;
    private Date dateOfBirth;
    private String email;
    private Gender gender;
    private String aadharNumber;
    private String pan;
    private String maritalStatus;
    private Date dateOfJoining = new Date();
    private boolean isActive = true;

    public static FacultyResponseDTO generateFacultyResponseDTO(Faculty faculty) {

        if (faculty == null) {
            return FacultyResponseDTO.builder().build();
        }

        Long id = faculty.getId();
        String name = faculty.getName();
        String fatherName = faculty.getFatherName();
        String motherName = faculty.getMotherName();
        String contactNumber = faculty.getContactNumber();
        String backupContactNumber = faculty.getBackupContactNumber();
        Date dateOfBirth = faculty.getDateOfBirth();
        String email = faculty.getEmail();
        Gender gender = faculty.getGender();
        String aadharNumber = faculty.getAadharNumber();
        String pan = faculty.getPan();
        String maritalStatus = faculty.getMaritalStatus();
        Date dateOfJoining = faculty.getDateOfJoining();
        boolean isActive = faculty.isActive();

        return FacultyResponseDTO.builder()
                .id(id)
                .name(name)
                .fatherName(fatherName)
                .motherName(motherName)
                .contactNumber(contactNumber)
                .backupContactNumber(backupContactNumber)
                .dateOfBirth(dateOfBirth)
                .email(email)
                .gender(gender)
                .aadharNumber(aadharNumber)
                .pan(pan)
                .maritalStatus(maritalStatus)
                .dateOfJoining(dateOfJoining)
                .isActive(isActive)
                .build();
    }
}
