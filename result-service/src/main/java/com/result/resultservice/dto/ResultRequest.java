package com.result.resultservice.dto;

import lombok.Data;

@Data
public class ResultRequest {
    private Long studentId;
    private Long courseId;
    private String grade;
}
