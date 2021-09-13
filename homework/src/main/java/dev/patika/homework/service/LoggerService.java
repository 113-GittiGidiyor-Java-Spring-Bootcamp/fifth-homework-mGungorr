package dev.patika.homework.service;

import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.mappers.LoggerMapper;
import dev.patika.homework.model.Logger;
import dev.patika.homework.repository.LoggerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * This service has operations on api, you can do CRUD operations for Logs
 */
@Service
@RequiredArgsConstructor
public class LoggerService {

    private final LoggerDAO loggerDAO;
    private final LoggerMapper loggerMapper;

    /**
     * Logger Service for logging exceptions
     *
     * @param msg Error message
     * @param type Status Code of errors
     * @param date Date of Error
     * @return List<LoggerDTO>
     */
    public List<LoggerDTO> findByMessageOrTypeOrDate(String msg, String type, String date) {

        if (date == null) {
            List<LoggerDTO> loggerList = new ArrayList<>();
            List<Logger> loggers = loggerDAO.findByStatusCodeContaining(type);
            for (Logger logger : loggers) {
                LoggerDTO loggerDTO = loggerMapper.mapFromLoggertoLoggerDTO(logger);
                loggerList.add(loggerDTO);
            }
            return loggerList;

        } else {
            LocalDate localDate = LocalDate.parse(date);
            List<LoggerDTO> loggerList = new ArrayList<>();
            List<Logger> loggers = loggerDAO.findByStatusCodeContainingAndTimestampBetween(type, localDate.atStartOfDay(), localDate.plusDays(1).atStartOfDay());
            for (Logger logger : loggers) {
                LoggerDTO loggerDTO = loggerMapper.mapFromLoggertoLoggerDTO(logger);
                loggerList.add(loggerDTO);
            }
            return loggerList;
        }
    }
}
