package com.enrollment.enrollmentservice.dto;

import lombok.Data;

@Data
public class EnrollRequest {
    private Long studentId;
    private Long courseId;
}
