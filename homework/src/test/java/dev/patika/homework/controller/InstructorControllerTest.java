package dev.patika.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.SalaryDTO;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Instructor;
import dev.patika.homework.model.SalaryLogger;
import dev.patika.homework.service.InstructorService;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InstructorController.class})
@ExtendWith(SpringExtension.class)
class InstructorControllerTest {

    @Autowired
    private InstructorController instructorController;

    @MockBean
    private InstructorService instructorService;

    @Test
    void findAll() {
    }

    @Test
    void saveInstructor() {
    }

    @Test
    void findinstructorsById() {
    }

    @Test
    void testChangeSalary() throws Exception {
        Instructor instructor = new Instructor();
        instructor.setInstructorAdress("Instructor Adress");
        instructor.setCourseSet(new HashSet<Course>());
        instructor.setCreateTime(null);
        instructor.setId(1);
        instructor.setInstructorPhone(1L);
        instructor.setInstructorName("Instructor Name");
        instructor.setModifiedTime(null);
        when(this.instructorService.updateSalary(anyInt(), (SalaryDTO) any())).thenReturn(instructor);

        SalaryDTO salaryDTO = new SalaryDTO();
        salaryDTO.setSalaryUpdateType(SalaryDTO.SalaryUpdateType.INCREASE);
        salaryDTO.setPercentage(1);
    }

    @Test
    void testDeleteInstructorById() throws Exception {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setInstructorAdress("Instructor Adress");
        instructorDTO.setCreateTime(null);
        instructorDTO.setId(1);
        instructorDTO.setInstructorPhone(1L);
        instructorDTO.setInstructorName("Instructor Name");
        instructorDTO.setModifiedTime(null);
        when(this.instructorService.deleteById(anyInt())).thenReturn(instructorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/instructors/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.instructorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Deleted..."));
    }

    @Test
    void testFindinstructorsById() throws Exception {
//        InstructorDTO instructorDTO = new InstructorDTO();
//        instructorDTO.setInstructorAdress("Instructor Adress");
//        instructorDTO.setCreateTime(null);
//        instructorDTO.setId(1);
//        instructorDTO.setInstructorPhone(1L);
//        instructorDTO.setInstructorName("Instructor Name");
//        instructorDTO.setModifiedTime(null);
//        when(this.instructorService.findById(anyInt())).thenReturn(instructorDTO);
    }

    @Test
    void testSaveInstructor() throws Exception {
//        Instructor instructor = new Instructor();
//        instructor.setInstructorAdress("Instructor Adress");
//        instructor.setCourseSet(new HashSet<Course>());
//        instructor.setCreateTime(null);
//        instructor.setId(1);
//        instructor.setInstructorPhone(1L);
//        instructor.setInstructorName("Instructor Name");
//        instructor.setModifiedTime(null);
//        Optional<Instructor> ofResult = Optional.<Instructor>of(instructor);
//        when(this.instructorService.save((InstructorDTO) any())).thenReturn(ofResult);
//
//        InstructorDTO instructorDTO = new InstructorDTO();
//        instructorDTO.setInstructorAdress("Instructor Adress");
//        instructorDTO.setCreateTime(null);
//        instructorDTO.setId(1);
//        instructorDTO.setInstructorPhone(1L);
//        instructorDTO.setInstructorName("Instructor Name");
//        instructorDTO.setModifiedTime(null);
    }

    @Test
    void testSaveInstructor2() throws Exception {
        when(this.instructorService.save((InstructorDTO) any())).thenReturn(Optional.<Instructor>empty());

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setInstructorAdress("Instructor Adress");
        instructorDTO.setCreateTime(null);
        instructorDTO.setId(1);
        instructorDTO.setInstructorPhone(1L);
        instructorDTO.setInstructorName("Instructor Name");
        instructorDTO.setModifiedTime(null);
        String content = (new ObjectMapper()).writeValueAsString(instructorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/instructors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.instructorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testUpdateInstructor() throws Exception {
        Instructor instructor = new Instructor();
        instructor.setInstructorAdress("Instructor Adress");
        instructor.setCourseSet(new HashSet<Course>());
        instructor.setCreateTime(null);
        instructor.setId(1);
        instructor.setInstructorPhone(1L);
        instructor.setInstructorName("Instructor Name");
        instructor.setModifiedTime(null);
        Optional<Instructor> ofResult = Optional.<Instructor>of(instructor);
        when(this.instructorService.update((InstructorDTO) any(), anyInt())).thenReturn(ofResult);

        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setInstructorAdress("Instructor Adress");
        instructorDTO.setCreateTime(null);
        instructorDTO.setId(1);
        instructorDTO.setInstructorPhone(1L);
        instructorDTO.setInstructorName("Instructor Name");
        instructorDTO.setModifiedTime(null);
    }

    @Test
    void updateInstructor() {
    }

    @Test
    void deleteInstructorById() {
    }

    @Test
    void testFindAll() throws Exception {
        when(this.instructorService.findAll()).thenReturn(new ArrayList<InstructorDTO>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/instructors");
        MockMvcBuilders.standaloneSetup(this.instructorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testFindAll2() throws Exception {
        when(this.instructorService.findAll()).thenReturn(new ArrayList<InstructorDTO>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/instructors");
        getResult.contentType("deneme123 123 1231");
        MockMvcBuilders.standaloneSetup(this.instructorController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void changeSalary() {
    }

    @Test
    void getAllTransactionsWithDate() {
    }
}