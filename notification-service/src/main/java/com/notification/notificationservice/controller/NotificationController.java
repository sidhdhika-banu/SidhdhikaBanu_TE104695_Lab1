package com.notification.notificationservice.controller;

import com.notification.notificationservice.dto.EnrollmentNotificationRequest;
import com.notification.notificationservice.entity.NotificationLog;
import com.notification.notificationservice.repository.NotificationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private NotificationLogRepository repo;

    /**
     * Expected POST to: /notify/enrollment
     * Body: { "studentId": 1, "courseId": 2 }
     */
    @PostMapping("/enrollment")
    public Map<String, String> notifyEnrollment(@RequestBody EnrollmentNotificationRequest req) {

        Long sid = req.getStudentId();
        Long cid = req.getCourseId();

        String msg = String.format("Student %d enrolled into Course %d", sid, cid);

        // Print to console (as requirement)
        System.out.println(msg);

        // Save log to H2 DB
        NotificationLog log = new NotificationLog();
        log.setStudentId(sid);
        log.setCourseId(cid);
        log.setMessage(msg);
        log.setCreatedAt(LocalDateTime.now());
        repo.save(log);

        return Map.of("message", msg);
    }
}
