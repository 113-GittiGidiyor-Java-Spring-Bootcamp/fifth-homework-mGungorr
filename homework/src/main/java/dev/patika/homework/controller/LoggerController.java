package dev.patika.homework.controller;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoggerController {

    private final LoggerService loggerService;

    @GetMapping("/log")
    public ResponseEntity<?> getExceptionLogs(@RequestParam(required = false) String msg, @RequestParam String type, @RequestParam(required = false) @DateTimeFormat(pattern = "YYYY-MM-DD") String date) {
        List<LoggerDTO> result = loggerService.findByMessageOrTypeOrDate(msg,type, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/*")
    public String errorPage() {
        return "There is no page like this. Check url and parameter type please!";
    }
}
