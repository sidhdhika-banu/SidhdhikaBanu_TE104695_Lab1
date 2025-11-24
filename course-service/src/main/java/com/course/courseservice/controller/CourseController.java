package com.course.courseservice.controller;

import com.course.courseservice.entity.Course;
import com.course.courseservice.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Course> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return repo.save(course);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
