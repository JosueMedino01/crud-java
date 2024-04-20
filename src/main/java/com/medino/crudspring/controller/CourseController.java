package com.medino.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medino.crudspring.model.Course;
import com.medino.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





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

  @GetMapping("/{id}")
  public ResponseEntity<Course> findByIdString(@PathVariable("id") Long id) {
      return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build()); 
  }
  

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody Course course){
    
    return courseRepository.save(course);
    /* return ResponseEntity.status(HttpStatus.CREATED)
      .body(courseRepository.save(course)); */
  }
}
