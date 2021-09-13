package dev.patika.homework.exceptions;

import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.mappers.LoggerMapper;
import dev.patika.homework.model.Logger;
import dev.patika.homework.repository.LoggerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LoggerDAO loggerDAO;
    private final LoggerMapper loggerMapper;

    @ExceptionHandler({CourseIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LoggerDTO> handleException(CourseIsAlreadyExistException courseIsAlreadyExistException) {
        Logger exception = new Logger(courseIsAlreadyExistException.getMessage());
        loggerDAO.save(exception);
        LoggerDTO response = loggerMapper.mapFromLoggertoLoggerDTO(exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InstructorIsAlreadyExistException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LoggerDTO> handleException(InstructorIsAlreadyExistException instructorIsAlreadyExistException) {
        Logger exception = new Logger(instructorIsAlreadyExistException.getMessage());
        loggerDAO.save(exception);
        LoggerDTO response = loggerMapper.mapFromLoggertoLoggerDTO(exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentAgeNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LoggerDTO> handleException(StudentAgeNotValidException studentAgeNotValidException) {
        Logger exception = new Logger(studentAgeNotValidException.getMessage());
        loggerDAO.save(exception);
        LoggerDTO response = loggerMapper.mapFromLoggertoLoggerDTO(exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({StudentNumberForOneCourseExceededException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LoggerDTO> handleException(StudentNumberForOneCourseExceededException studentNumberForOneCourseExceededException) {
        Logger exception = new Logger(studentNumberForOneCourseExceededException.getMessage());
        loggerDAO.save(exception);
        LoggerDTO response = loggerMapper.mapFromLoggertoLoggerDTO(exception);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
