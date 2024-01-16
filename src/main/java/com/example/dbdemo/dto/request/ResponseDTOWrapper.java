package com.example.dbdemo.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDTOWrapper<T> {
    private String errorMessage;
    private List<T> items;
}
