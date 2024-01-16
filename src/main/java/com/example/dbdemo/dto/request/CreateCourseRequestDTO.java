package com.example.dbdemo.dto.request;

import com.example.dbdemo.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequestDTO {
    private Long courseId;

    @NotBlank(message = "Course Name should not be null or empty")
    @Size(max = 100, message = "Course Name should not exceed 100 characters.")
    private String courseName;

    public Course generateCourse() {
        Long courseId = this.getCourseId();
        String courseName = this.getCourseName();

        return Course.builder()
                .courseId(courseId)
                .courseName(courseName)
                .build();
    }
}
