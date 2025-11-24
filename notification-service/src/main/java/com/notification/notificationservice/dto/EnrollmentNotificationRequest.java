package com.notification.notificationservice.dto;

import lombok.Data;

@Data
public class EnrollmentNotificationRequest {
    private Long studentId;
    private Long courseId;
}
