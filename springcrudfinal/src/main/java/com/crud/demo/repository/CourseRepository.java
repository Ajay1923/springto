package com.crud.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.crud.demo.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}

