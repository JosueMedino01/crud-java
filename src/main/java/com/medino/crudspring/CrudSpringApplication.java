package com.medino.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.medino.crudspring.enums.Category;
import com.medino.crudspring.model.Course;
import com.medino.crudspring.model.Lesson;
import com.medino.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

  @Bean
  CommandLineRunner initDataBase(CourseRepository courseRepository){
    return args -> {
      courseRepository.deleteAll();

      Course c = new Course();
      c.setName("Angular com Srping");
      c.setCategory(Category.FRONTEND);

      Lesson lesson = new Lesson();

      lesson.setName("Introdução a py");
      lesson.setYoutubeURL("youtube");
      lesson.setCourse(c);
      c.getLessons().add(lesson);

      Lesson lesson2 = new Lesson();

      lesson2.setName("Introdução a JAVA");
      lesson2.setYoutubeURL("AVAJ");
      lesson2.setCourse(c);
      c.getLessons().add(lesson2);

      courseRepository.save(c);
    };
  }

}
