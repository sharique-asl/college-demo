package com.example.dbdemo.controller;

import com.example.dbdemo.dto.request.ResponseDTOWrapper;
import com.example.dbdemo.dto.response.FacultyResponseDTO;
import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.service.FacultyService;
import com.example.dbdemo.utilities.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faculty")
@Slf4j
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @PostMapping(value = "/getAllFaculties", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<FacultyResponseDTO>> getAllFaculties(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "sort", required = false) String sort
    ) {
        try {
            List<Faculty> faculties = facultyService.getAllFaculties(name, gender, sort);

            List<FacultyResponseDTO> facultiesDTOs = faculties
                    .stream()
                    .map(FacultyResponseDTO::generateFacultyResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<FacultyResponseDTO>builder()
                                    .items(facultiesDTOs)
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<FacultyResponseDTO>builder()
                                    .errorMessage("Fetching faculties was unsuccessful")
                                    .build()
                    );
        }
    }

    @GetMapping(value = "/getFacultiesByIds", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<FacultyResponseDTO>> getFacultiesByIds(@NotNull @RequestParam List<Long> id) {
        try {
            List<Faculty> faculties = facultyService.getFacultiesByIds(id);

            List<FacultyResponseDTO> facultiesDTOs = faculties
                    .stream()
                    .map(FacultyResponseDTO::generateFacultyResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<FacultyResponseDTO>builder()
                                    .items(facultiesDTOs)
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<FacultyResponseDTO>builder()
                                    .errorMessage("Unable to fetch faculties using IDs")
                                    .build()
                    );
        }
    }

    @PostMapping(value = "/createFaculty", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createFaculty(@Valid @RequestBody Faculty faculty) {
        try {
            Faculty createdFaculty = facultyService.createFaculty(faculty);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Success");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failure");
        }
    }

    @PutMapping(value = "/updateFaculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<String>> updateFaculty(@Valid @RequestBody Faculty faculty) {
        try {
            Faculty updatedFaculty = facultyService.updateFaculty(faculty);

            if (updatedFaculty == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(
                                ResponseDTOWrapper.<String>builder()
                                        .errorMessage("Faculty to be updated not found")
                                        .build()
                        );
            }

            FacultyResponseDTO updatedFacultyDTO = FacultyResponseDTO.generateFacultyResponseDTO(updatedFaculty);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList("Faculty updated with id:" + updatedFacultyDTO.getId()))
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .errorMessage("Unable to update faculty")
                                    .build()
                    );
        }
    }

    @DeleteMapping(value = "/deleteFaculty/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTOWrapper<String>> deleteFaculty(@NotNull @Min(1) @PathVariable Long id) {
        try {
            Boolean status = facultyService.deleteFaculty(id);

            return ResponseEntity
                    .status(status ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .items(Collections.singletonList(status ? "Deletion successful" : "Deletion Failed"))
                                    .build()
                    );

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ResponseDTOWrapper.<String>builder()
                                    .errorMessage("Unable to delete faculty")
                                    .build()
                    );
        }
    }
}
