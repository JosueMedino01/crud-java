package com.medino.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.medino.crudspring.dto.CourseDTO;
import com.medino.crudspring.dto.mapper.CourseMapper;
import com.medino.crudspring.exception.RecordNotFoundExeption;
import com.medino.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list(){
        return courseRepository.findAll()
        .stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO findById(@PathVariable("id") @NotNull @Positive Long id) {
      return courseRepository.findById(id).map(courseMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundExeption(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course){
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update (@NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO course){
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.name());
                recordFound.setCategory(course.category());
                return courseMapper.toDTO(courseRepository.save(recordFound));
            }).orElseThrow(() -> new RecordNotFoundExeption(id));
    } 

    public void delete(@NotNull @Positive Long id){
       courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundExeption(id)));
    }
}
