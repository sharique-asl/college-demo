package com.example.dbdemo.dto.response;

public class CourseResponseDTO {

    private Long courseId;
    private String courseName;

    public CourseResponseDTO(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

}
