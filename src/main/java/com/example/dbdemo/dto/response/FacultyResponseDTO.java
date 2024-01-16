package com.example.dbdemo.dto.response;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name should not be null or empty")
    @Size(max = 255, message = "Name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name should contain only letters.")
    private String name;

    @NotBlank(message = "Father's name should not be null or empty")
    @Size(max = 255, message = "Father's name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Father's name should contain only letters.")
    private String fatherName;

    @NotBlank(message = "Mother's name should not be null or empty")
    @Size(max = 255, message = "Mother's name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Mother's name should contain only letters.")
    private String motherName;

    @NotBlank
    @Size(min = 10, max = 10, message = "Contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Contact number should not contain special characters.")
    private String contactNumber;

    @Size(min = 10, max = 10, message = "Backup contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Backup contact number should not contain special characters.")
    private String backupContactNumber;

    @NotNull
    @Past(message = "Date of birth should be in the past.")
    private LocalDate dateOfBirth;

    @NotBlank
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(min = 12, max = 12, message = "Aadhar number should be 12 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Aadhar number should not contain special characters.")
    private String aadharNumber;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "PAN should be alphanumeric with no special characters.")
    @Size(min = 10, max = 10, message = "PAN should be 10 characters.")
    private String pan;

    private String maritalStatus;

    @PastOrPresent(message = "Date of joining should be in the past or present.")
    @Builder.Default
    private LocalDate dateOfJoining = LocalDate.now();

    @Builder.Default
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
        LocalDate dateOfBirth = faculty.getDateOfBirth();
        String email = faculty.getEmail();
        Gender gender = faculty.getGender();
        String aadharNumber = faculty.getAadharNumber();
        String pan = faculty.getPan();
        String maritalStatus = faculty.getMaritalStatus();
        LocalDate dateOfJoining = faculty.getDateOfJoining();
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
