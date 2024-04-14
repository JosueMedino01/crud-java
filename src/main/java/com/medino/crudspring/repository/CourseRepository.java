package com.medino.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medino.crudspring.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
  
}
