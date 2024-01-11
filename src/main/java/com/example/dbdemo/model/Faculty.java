package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="faculty")
public class Faculty {
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
    @Temporal(TemporalType.DATE)
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


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "faculty_details_id", referencedColumnName = "id")
//    private FacultyDetails facultyDetails;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

//    @OneToMany(mappedBy = "faculty")
//    private Set<Course> courses;
}

//no trackability for changes in db

/*

[
  {
    "id": 1,
    "name": "John Doe",
    "fatherName": "Michael Doe",
    "motherName": "Emily Doe",
    "contactNumber": "1234567890",
    "backupContactNumber": "9876543210",
    "dateOfBirth": "1990-01-15",
    "email": "john.doe@example.com",
    "gender": "MALE",
    "aadharNumber": "123456789012",
    "pan": "ABCDE1234F",
    "maritalStatus": "Single",
    "dateOfJoining": "2015-05-20",
    "isActive": true
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "fatherName": "Robert Smith",
    "motherName": "Susan Smith",
    "contactNumber": "9876543210",
    "backupContactNumber": "1234567890",
    "dateOfBirth": "1985-08-23",
    "email": "jane.smith@example.com",
    "gender": "FEMALE",
    "aadharNumber": "987654321098",
    "pan": "XYZW0987M",
    "maritalStatus": "Married",
    "dateOfJoining": "2018-03-10",
    "isActive": false
  }
  // Add more entries as needed
]



 */