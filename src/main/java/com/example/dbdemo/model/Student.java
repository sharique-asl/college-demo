package com.example.dbdemo.model;

import com.example.dbdemo.utilities.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @NotNull
//    @Digits(integer = 6, fraction = 0, message = "Roll number should be a number with up to 6 digits.")
    private Long rollNumber;

//    @NotBlank(message = "Name should not be null or empty")
//    @Size(max = 255, message = "Name should not exceed 255 characters.")
//    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name should contain only letters.")
    private String name;

//    @NotBlank(message = "Father's name should not be null or empty")
//    @Size(max = 255, message = "Father's name should not exceed 255 characters.")
//    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Father's name should contain only letters.")
    private String fatherName;

//    @NotBlank(message = "Mother's name should not be null or empty")
//    @Size(max = 255, message = "Mother's name should not exceed 255 characters.")
//    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Mother's name should contain only letters.")
    private String motherName;

//    @NotBlank
//    @Size(min = 10, max = 10, message = "Contact number should be 10 digits.")
//    @Pattern(regexp = "^[0-9]*$", message = "Contact number should not contain special characters.")
    private String contactNumber;

//    @Size(min = 10, max = 10, message = "Backup contact number should be 10 digits.")
//    @Pattern(regexp = "^[0-9]*$", message = "Backup contact number should not contain special characters.")
    private String backupContactNumber;

//    @Size(min = 10, max = 10, message = "Father's contact number should be 10 digits.")
//    @Pattern(regexp = "^[0-9]*$", message = "Father's contact number should not contain special characters.")
//    @Column(name = "father_contact_number")
    private String fatherContactNumber;

//    @NotNull
//    @Past(message = "Date of birth should be in the past.")
//    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

//    @NotBlank
//    @Email(message = "Invalid email format.")
    private String email;

//    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

//    @Size(min = 12, max = 12, message = "Aadhar number should be 12 digits.")
//    @Pattern(regexp = "^[0-9]*$", message = "Aadhar number should not contain special characters.")
    private String aadharNumber;

//    @NotBlank
//    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "PAN should be alphanumeric with no special characters.")
//    @Size(min = 10, max = 10, message = "PAN should be 10 characters.")
    private String pan;

    @Builder.Default
    private boolean isActive = true;

}


/*

[
  {
    "id": 1,
    "rollNumber": 123456,
    "name": "Alice Brown",
    "fatherName": "John Brown",
    "motherName": "Mary Brown",
    "contactNumber": "9876543210",
    "backupContactNumber": "1234567890",
    "fatherContactNumber": "1112233445",
    "dateOfBirth": "1995-02-10",
    "email": "alice.brown@example.com",
    "gender": "FEMALE",
    "aadharNumber": "987654321012",
    "pan": "ABCDE1234F",
    "isActive": true
  },
  {
    "id": 2,
    "rollNumber": 234567,
    "name": "Bob Johnson",
    "fatherName": "Michael Johnson",
    "motherName": "Laura Johnson",
    "contactNumber": "8765432109",
    "backupContactNumber": "3210987654",
    "fatherContactNumber": "5544332211",
    "dateOfBirth": "1998-05-18",
    "email": "bob.johnson@example.com",
    "gender": "MALE",
    "aadharNumber": "123456789098",
    "pan": "XYZW0987M",
    "isActive": false
  }
  // Add more entries as needed
]


 */