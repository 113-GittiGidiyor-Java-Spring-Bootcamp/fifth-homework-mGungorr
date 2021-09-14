package dev.patika.homework.controller;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.model.Course;
import dev.patika.homework.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    CourseService mockCourseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void findAll() {
//        Mockito.when(mockCourseService.findAll()).thenReturn();
    }

    @Test
    void saveCourse() {
        Course course= new Course();
        Optional<Course> resultOptional = Optional.of(course);

        Mockito.when(mockCourseService.save(Mockito.any())).thenReturn(resultOptional);

        CourseDTO dto = new CourseDTO();
        Course actual = this.courseController.saveCourse(dto).getBody();

        assertNotNull(actual);
    }

    @Test
    void findCourseById() {
    }

    @Test
    void updateCourse() {
    }

    @Test
    void deleteCourseById() {
    }
}