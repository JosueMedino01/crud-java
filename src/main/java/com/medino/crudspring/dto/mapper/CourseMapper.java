package com.medino.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.medino.crudspring.dto.CourseDTO;
import com.medino.crudspring.enums.Category;
import com.medino.crudspring.model.Course;

@Component
public class CourseMapper {
    public CourseDTO toDTO (Course course){
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO){
        Course course = new Course();

        if(courseDTO.id() != null){ 
            course.setId(courseDTO.id());
        };

        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));

        return course;
    }

    public Category convertCategoryValue(String value){
        return switch (value){
            case "Front-end" -> Category.FRONTEND;
            case "Back-end" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Categoria Inválida: " + value);
        };
    }
}
