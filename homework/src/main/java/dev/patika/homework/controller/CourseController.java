package dev.patika.homework.controller;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import dev.patika.homework.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> findAll() {
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDTO courseDTO) {
        Optional<Course> resultOptional = courseService.save(courseDTO);
        if (resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> findCourseById(@PathVariable int id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO){
        return new ResponseEntity<>(courseService.update(courseDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourseById(@PathVariable int id){
        courseService.deleteById(id);
        return "Deleted...";
    }
}
