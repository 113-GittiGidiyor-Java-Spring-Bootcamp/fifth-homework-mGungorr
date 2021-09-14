package dev.patika.homework.service;

import dev.patika.homework.mappers.CourseMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.repository.CourseDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseDAO mockCourseDAO;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseService courseService;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
        Course course = new Course();

    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}