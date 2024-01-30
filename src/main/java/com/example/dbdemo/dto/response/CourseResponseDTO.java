package com.example.dbdemo.dto.response;

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
@Builder
public class CourseResponseDTO {
    private Long courseId;
    private String courseName;

    public static CourseResponseDTO generateCourseResponseDTO(Course course) {

        if (course == null) {
            return CourseResponseDTO
                    .builder()
                    .build();
        }
        Long courseId = course.getCourseId();
        String courseName = course.getCourseName();

        return CourseResponseDTO
                .builder()
                    .courseId(courseId)
                    .courseName(courseName)
                .build();
    }
}
