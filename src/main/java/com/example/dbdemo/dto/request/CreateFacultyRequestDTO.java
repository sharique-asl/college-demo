package com.example.dbdemo.dto.request;

import com.example.dbdemo.dto.response.FacultyResponseDTO;
import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFacultyRequestDTO {
    @NotNull
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
    private LocalDate dateOfJoining = LocalDate.now();

    private boolean isActive = true;

    public Faculty generateFaculty() {
        Long id = this.getId();
        String name = this.getName();
        String fatherName = this.getFatherName();
        String motherName = this.getMotherName();
        String contactNumber = this.getContactNumber();
        String backupContactNumber = this.getBackupContactNumber();
        LocalDate dateOfBirth = this.getDateOfBirth();
        String email = this.getEmail();
        Gender gender = this.getGender();
        String aadharNumber = this.getAadharNumber();
        String pan = this.getPan();
        String maritalStatus = this.getMaritalStatus();
        LocalDate dateOfJoining = this.getDateOfJoining();
        boolean isActive = this.isActive();

        return Faculty.builder()
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
