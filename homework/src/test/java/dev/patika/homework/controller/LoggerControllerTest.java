package dev.patika.homework.controller;

import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.service.LoggerService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LoggerController.class})
@ExtendWith(SpringExtension.class)
class LoggerControllerTest {

    @Autowired
    private LoggerController loggerController;

    @MockBean
    private LoggerService loggerService;

    @Test
    void getExceptionLogs() {
    }

    @Test
    void errorPage() {
    }

    @Test
    void testGetExceptionLogs() throws Exception {
        when(this.loggerService.findByMessageOrTypeOrDate((String) any(), (String) any(), (String) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/log").param("type", "deneme");
        MockMvcBuilders.standaloneSetup(this.loggerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}