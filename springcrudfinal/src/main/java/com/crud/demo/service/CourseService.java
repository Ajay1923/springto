package com.crud.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.demo.entity.Course;
import com.crud.demo.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepository repository;

    // Fetch all courses
    public List<Course> findAllCourses() {
        return (List<Course>) repository.findAll();
    }

    // Fetch a course by ID
    public Course findCourseById(int id) {
        Optional<Course> result = repository.findById(id);
        return result.orElse(null); // Return null if not found
    }

    // Add a new course
    public String addCourse(Course course) {
       
        if (repository.existsById(course.getId())) {
            return "ID already exists"; // Return an error message
        }
        repository.save(course); // Save the course if ID does not exist
        return null; // No error
    }

    // Update an existing course
    public String updateCourse(Course course) {
        Optional<Course> result = repository.findById(course.getId());
        if (result.isPresent()) {
            Course existing = result.get();
            existing.setTech(course.getTech());
            existing.setName(course.getName());
            existing.setNoOfDays(course.getNoOfDays());
            repository.save(existing); // Update the existing course
            return null; // No error
        }
        return "Course not found"; 
    }

    // Delete a course by ID
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
