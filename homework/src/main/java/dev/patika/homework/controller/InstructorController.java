package dev.patika.homework.controller;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.SalaryDTO;
import dev.patika.homework.model.Instructor;
import dev.patika.homework.service.InstructorService;
import dev.patika.homework.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class InstructorController {

    private final InstructorService instructorService;


    @GetMapping("/instructors")
    public ResponseEntity<List<InstructorDTO>> findAll() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/instructors")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        Optional<Instructor> resultOptional = instructorService.save(instructorDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<InstructorDTO> findinstructorsById(@PathVariable int id) {
        return new ResponseEntity<>(instructorService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody InstructorDTO instructorDTO) {
        return new ResponseEntity<>(instructorService.update(instructorDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/instructors/{id}")
    public String deleteInstructorById(@PathVariable int id) {
        instructorService.deleteById(id);
        return "Deleted...";
    }

    @PutMapping("/instructors/salary/{id}")
    public ResponseEntity<Instructor> changeSalary(@PathVariable int id, @RequestBody SalaryDTO salaryDTO) {
        return new ResponseEntity<>(instructorService.updateSalary(id, salaryDTO), HttpStatus.OK);
    }

}
