package com.example.dbdemo.dto.request;

import com.example.dbdemo.dto.response.StudentResponseDTO;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequestDTO {
    private Long id;

    @Digits(integer = 6, fraction = 0, message = "Roll number should be a number with up to 6 digits.")
    private Long rollNumber;

    @Size(max = 255, message = "Name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name should contain only letters.")
    private String name;

    @Size(max = 255, message = "Father's name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Father's name should contain only letters.")
    private String fatherName;

    @Size(max = 255, message = "Mother's name should not exceed 255 characters.")
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Mother's name should contain only letters.")
    private String motherName;

    @Size(min = 10, max = 10, message = "Contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Contact number should not contain special characters.")
    private String contactNumber;

    @Size(min = 10, max = 10, message = "Backup contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Backup contact number should not contain special characters.")
    private String backupContactNumber;

    @Size(min = 10, max = 10, message = "Father's contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Father's contact number should not contain special characters.")
    @Column(name = "father_contact_number")
    private String fatherContactNumber;

    @Past(message = "Date of birth should be in the past.")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Email(message = "Invalid email format.")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Size(min = 12, max = 12, message = "Aadhar number should be 12 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Aadhar number should not contain special characters.")
    private String aadharNumber;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "PAN should be alphanumeric with no special characters.")
    @Size(min = 10, max = 10, message = "PAN should be 10 characters.")
    private String pan;

    private boolean isActive = true;

    public Student generateStudent() {

        Long id = this.getId();
        Long rollNumber = this.getRollNumber();
        String name = this.getName();
        String fatherName = this.getFatherName();
        String motherName = this.getMotherName();
        String contactNumber = this.getContactNumber();
        String backupContactNumber = this.getBackupContactNumber();
        String fatherContactNumber = this.getFatherContactNumber();
        Date dateOfBirth = this.getDateOfBirth();
        String email = this.getEmail();
        Gender gender = this.getGender();
        String aadharNumber = this.getAadharNumber();
        String pan = this.getPan();
        boolean isActive = this.isActive();

        return Student
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
