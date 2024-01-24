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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Digits(integer = 6, fraction = 0, message = "Roll number should be a number with up to 6 digits.")
    private Long rollNumber;

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

    @Size(min = 10, max = 10, message = "Father's contact number should be 10 digits.")
    @Pattern(regexp = "^[0-9]*$", message = "Father's contact number should not contain special characters.")
    @Column(name = "father_contact_number")
    private String fatherContactNumber;

    @NotNull
    @Past(message = "Date of birth should be in the past.")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

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
