package com.example.dbdemo.dto.request;

import com.example.dbdemo.dto.response.StudentResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDTOWrapper {
//    private HttpStatus code;

    private String errorMessage;
    private List<StudentResponseDTO> students;
}
