package dev.patika.homework.service;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.StudentAgeNotValidException;
import dev.patika.homework.mappers.StudentMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.model.enumaration.Gender;
import dev.patika.homework.repository.StudentDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;

import org.hibernate.internal.util.collections.JoinedIterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StudentService.class, StudentMapper.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @MockBean
    private StudentDAO studentDAO;

    @MockBean
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Test
    void testFindAll() {
        Iterable<Student> iterable = (Iterable<Student>) mock(Iterable.class);
        when(iterable.iterator()).thenReturn(new JoinedIterator<Student>(new ArrayList<Iterator<Student>>()));
        when(this.studentDAO.findAll()).thenReturn(iterable);
        assertTrue(this.studentService.findAll().isEmpty());
        verify(this.studentDAO).findAll();
        verify(iterable).iterator();
    }

    @Test
    void testFindById() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentBirthDate(mock(Date.class));
        studentDTO.setStudentAdress("Student Adress");
        studentDTO.setStudentName("Student Name");
        studentDTO.setCreateTime(null);
        studentDTO.setId(1);
        studentDTO.setGender(Gender.MALE);
        studentDTO.setModifiedTime(null);
        when(this.studentMapper.mapFromStudenttoStudentDTO((Student) any())).thenReturn(studentDTO);

        Student student = new Student();
        student.setStudentBirthDate(mock(Date.class));
        student.setStudentAdress("Student Adress");
        student.setStudentName("Student Name");
        student.setStudentCourses(new HashSet<Course>());
        student.setCreateTime(null);
        student.setId(1);
        student.setGender(Gender.MALE);
        student.setModifiedTime(null);
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentDAO.findById((Integer) any())).thenReturn(ofResult);
        assertSame(studentDTO, this.studentService.findById(1));
        verify(this.studentMapper).mapFromStudenttoStudentDTO((Student) any());
        verify(this.studentDAO).findById((Integer) any());
    }

    @Test
    void testSave() {
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
        assertThrows(StudentAgeNotValidException.class, () -> this.studentService.save(studentDTO));
        verify(date).getTime();
    }

    @Test
    void testDeleteById() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentBirthDate(mock(Date.class));
        studentDTO.setStudentAdress("Student Adress");
        studentDTO.setStudentName("Student Name");
        studentDTO.setCreateTime(null);
        studentDTO.setId(1);
        studentDTO.setGender(Gender.MALE);
        studentDTO.setModifiedTime(null);
        when(this.studentMapper.mapFromStudenttoStudentDTO((Student) any())).thenReturn(studentDTO);

        Student student = new Student();
        student.setStudentBirthDate(mock(Date.class));
        student.setStudentAdress("Student Adress");
        student.setStudentName("Student Name");
        student.setStudentCourses(new HashSet<Course>());
        student.setCreateTime(null);
        student.setId(1);
        student.setGender(Gender.MALE);
        student.setModifiedTime(null);
        Optional<Student> ofResult = Optional.<Student>of(student);
        doNothing().when(this.studentDAO).deleteById((Integer) any());
        when(this.studentDAO.findById((Integer) any())).thenReturn(ofResult);
        assertSame(studentDTO, this.studentService.deleteById(1));
        verify(this.studentMapper).mapFromStudenttoStudentDTO((Student) any());
        verify(this.studentDAO).deleteById((Integer) any());
        verify(this.studentDAO).findById((Integer) any());
    }

    @Test
    void testUpdate() {
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
        assertThrows(StudentAgeNotValidException.class, () -> this.studentService.update(studentDTO, 1));
        verify(date).getTime();
    }


    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {

    }

    @Test
    void calculateAgeFromBirthDate() {
    }

    @Test
    void testCalculateAgeFromBirthDate() {
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        assertThrows(StudentAgeNotValidException.class, () -> this.studentService.calculateAgeFromBirthDate(date));
        verify(date).getTime();
    }

}