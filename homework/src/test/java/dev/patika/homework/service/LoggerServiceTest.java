package dev.patika.homework.service;

import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.mappers.LoggerMapper;
import dev.patika.homework.model.Logger;
import dev.patika.homework.repository.LoggerDAO;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LoggerService.class, LoggerMapper.class})
@ExtendWith(SpringExtension.class)
class LoggerServiceTest {

    @MockBean
    private LoggerDAO loggerDAO;

    @MockBean
    private LoggerMapper loggerMapper;

    @Autowired
    private LoggerService loggerService;

    @Test
    void findByMessageOrTypeOrDate() {
    }

    @Test
    void testFindByMessageOrTypeOrDate() {
        when(this.loggerDAO.findByStatusCodeContainingAndTimestampBetween(any(), any(),
                any())).thenReturn(new ArrayList<>());
        assertTrue(this.loggerService.findByMessageOrTypeOrDate("Msg", "Type", "2020-03-01").isEmpty());
        verify(this.loggerDAO).findByStatusCodeContainingAndTimestampBetween((String) any(),
                any(), any());
    }
}