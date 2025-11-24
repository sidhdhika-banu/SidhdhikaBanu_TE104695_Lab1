package com.enrollment.enrollmentservice.controller;

import com.enrollment.enrollmentservice.dto.EnrollRequest;
import com.enrollment.enrollmentservice.entity.Enrollment;
import com.enrollment.enrollmentservice.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    private final String STUDENT_URL = "http://localhost:8081/students/";
    private final String COURSE_URL = "http://localhost:8082/courses/";
    private final String NOTIFY_URL = "http://localhost:8085/notify/enrollment";

    @PostMapping
    public Map<String, String> enroll(@RequestBody EnrollRequest req) {

        // Validate Student
        Object student = restTemplate.getForObject(STUDENT_URL + req.getStudentId(), Object.class);
        if (student == null) {
            return Map.of("message", "Invalid Student ID");
        }

        // Validate Course
        Object course = restTemplate.getForObject(COURSE_URL + req.getCourseId(), Object.class);
        if (course == null) {
            return Map.of("message", "Invalid Course ID");
        }

        // Save enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(req.getStudentId());
        enrollment.setCourseId(req.getCourseId());
        repo.save(enrollment);

        // Notify Service
        Map<String, Object> notifyBody = new HashMap<>();
        notifyBody.put("studentId", req.getStudentId());
        notifyBody.put("courseId", req.getCourseId());

        restTemplate.postForObject(NOTIFY_URL, notifyBody, String.class);

        return Map.of("message", "Enrollment Successful!");
    }

    @GetMapping("/student/{id}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }
}
