package com.result.resultservice.controller;

import com.result.resultservice.dto.ResultRequest;
import com.result.resultservice.entity.Result;
import com.result.resultservice.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    private final String STUDENT_URL = "http://localhost:8081/students/";
    private final String COURSE_URL = "http://localhost:8082/courses/";

    @PostMapping
    public Map<String, String> saveResult(@RequestBody ResultRequest req) {

        // Validate student
        Object student = restTemplate.getForObject(STUDENT_URL + req.getStudentId(), Object.class);
        if (student == null) {
            return Map.of("message", "Invalid Student ID");
        }

        // Validate course
        Object course = restTemplate.getForObject(COURSE_URL + req.getCourseId(), Object.class);
        if (course == null) {
            return Map.of("message", "Invalid Course ID");
        }

        // Save result
        Result result = new Result();
        result.setStudentId(req.getStudentId());
        result.setCourseId(req.getCourseId());
        result.setGrade(req.getGrade());

        repo.save(result);

        return Map.of("message", "Result saved successfully!");
    }

    @GetMapping("/student/{id}")
    public List<Result> getResultsByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }
}
