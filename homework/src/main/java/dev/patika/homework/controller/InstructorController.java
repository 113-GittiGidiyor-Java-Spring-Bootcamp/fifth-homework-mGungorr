package dev.patika.homework.controller;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.model.Instructor;
import dev.patika.homework.model.Student;
import dev.patika.homework.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InstructorController {

    InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<InstructorDTO>> findAll() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/instructors")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        Optional<Instructor> resultOptional = instructorService.save(instructorDTO);
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<InstructorDTO> findinstructorsById(@PathVariable int id){
        return new ResponseEntity<>(instructorService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody InstructorDTO instructorDTO) {
        return new ResponseEntity<>(instructorService.update(instructorDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/instructors/{id}")
    public String deleteInstructorById(@PathVariable int id){
        instructorService.deleteById(id);
        return "Deleted...";
    }

}
