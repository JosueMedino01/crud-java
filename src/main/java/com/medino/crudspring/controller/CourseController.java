package com.medino.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medino.crudspring.model.Course;
import com.medino.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public List<Course> list(){
    return courseService.list();
  }

  @GetMapping("/{id}")
  public Course findById(@PathVariable("id") @NotNull @Positive Long id) {
    return courseService.findById(id);
  }
  

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody @Valid Course course){
    return courseService.create(course);
  }

  @PutMapping("/{id}")
  public Course update (@PathVariable("id") @NotNull @Positive Long id, @RequestBody @Valid Course course){
    return courseService.update(id, course);
  } 

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") @NotNull @Positive Long id){
    courseService.delete(id);  
  }
}
