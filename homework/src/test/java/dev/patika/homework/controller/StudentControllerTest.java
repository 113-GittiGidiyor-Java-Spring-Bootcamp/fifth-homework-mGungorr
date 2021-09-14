package dev.patika.homework.controller;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.mappers.StudentMapperImpl;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.model.enumaration.Gender;
import dev.patika.homework.repository.StudentDAO;
import dev.patika.homework.service.StudentService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    void findAll() {
    }

    @Test
    void saveStudent() {
    }

    @Test
    void findStudentById() {
    }

    @Test
    void testDeleteStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentBirthDate(mock(Date.class));
        studentDTO.setStudentAdress("Student Adress");
        studentDTO.setStudentName("Student Name");
        studentDTO.setCreateTime(null);
        studentDTO.setId(1);
        studentDTO.setGender(Gender.MALE);
        studentDTO.setModifiedTime(null);
        when(this.studentService.deleteById(anyInt())).thenReturn(studentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/students/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted..."));
    }


    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setStudentBirthDate(mock(Date.class));
        student.setStudentAdress("Student Adress");
        student.setStudentName("Student Name");
        student.setStudentCourses(new HashSet<Course>());
        student.setCreateTime(null);
        student.setId(1);
        student.setGender(Gender.MALE);
        student.setModifiedTime(null);
        StudentService studentService = mock(StudentService.class);
        when(studentService.save((StudentDTO) any())).thenReturn(Optional.<Student>of(student));
        StudentController studentController = new StudentController(studentService);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentBirthDate(date);
        studentDTO.setStudentAdress("Student Adress");
        studentDTO.setStudentName("Student Name");
        studentDTO.setCreateTime(null);
        studentDTO.setId(1);
        studentDTO.setGender(Gender.MALE);
        studentDTO.setModifiedTime(null);
        ResponseEntity<Student> actualSaveStudentResult = studentController.saveStudent(studentDTO);
        assertTrue(actualSaveStudentResult.getHeaders().isEmpty());
        assertTrue(actualSaveStudentResult.hasBody());
        assertEquals(HttpStatus.OK, actualSaveStudentResult.getStatusCode());
        verify(studentService).save((StudentDTO) any());
    }

}