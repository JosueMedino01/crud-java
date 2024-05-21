package com.medino.crudspring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medino.crudspring.model.Course;
import com.medino.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

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
  public ResponseEntity<Course> findByIdString(@PathVariable("id") @NotNull @Positive Long id) {
      return courseRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build()); 
  }
  

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody @Valid Course course){
    
    return courseRepository.save(course);
    /* return ResponseEntity.status(HttpStatus.CREATED)
      .body(courseRepository.save(course)); */
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> update (@PathVariable("id") @NotNull @Positive Long id, @RequestBody @Valid Course course){
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.getName());
          recordFound.setCategory(course.getCategory());
          Course updated = courseRepository.save(recordFound);
          return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build()); 
  } 

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") @NotNull @Positive Long id){
    return courseRepository.findById(id)
    .map(recordFound -> {
      courseRepository.deleteById(id);
      return ResponseEntity.noContent().<Void>build();
    })
    .orElse(ResponseEntity.notFound().build()); 
  }
}
