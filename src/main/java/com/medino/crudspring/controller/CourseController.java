package com.medino.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medino.crudspring.model.Course;
import com.medino.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

  /* @Autowired */
  private final CourseRepository courseRepository;

  /* public CourseController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  } */

  /*  @RequestMapping(method = RequestMethod.GET) */
  @GetMapping
  public List<Course> list(){
    return courseRepository.findAll();
  }
}
