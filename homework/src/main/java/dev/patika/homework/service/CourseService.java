package dev.patika.homework.service;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.exceptions.CourseIsAlreadyExistException;
import dev.patika.homework.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.homework.mappers.CourseMapper;
import dev.patika.homework.model.Course;
import dev.patika.homework.repository.CourseDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This service has operations on api, you can do CRUD operations for Courses and exceptions controls
 */
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDAO courseDAO;
    private final CourseMapper courseMapper;

    /**
     * Finds all Courses on database and get them as List<CourseDTO>
     *
     * @return List<CourseDTO>
     */
    @Transactional
    public List<CourseDTO> findAll() {
        List<CourseDTO> courseList = new ArrayList<>();
        Iterable<Course> courseIter = courseDAO.findAll();
        for (Course course : courseIter) {
            CourseDTO courseDTO = courseMapper.mapFromCoursetoCourseDTO(course);
            courseList.add(courseDTO);
        }
        return courseList;
    }
    /**
     * Finds courses by ID on database and get them as CourseDTO
     *
     * @param id id of the Course.
     * @return CourseDTO
     */
    @Transactional
    public CourseDTO findById(int id) {
        return courseMapper.mapFromCoursetoCourseDTO(courseDAO.findById(id).get());
    }

    /**
     * Saves courses to Database
     *
     * @param courseDTO
     * @return Optional<Course>
     */
    @Transactional
    public Optional<Course> save(CourseDTO courseDTO) {
        CourseExists(courseDTO.getCourseCode());
        NumberOfStudentsInCourse(courseDTO.getId());
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        course.setCreateTime(java.time.Clock.systemUTC().instant());
        course.setModifiedTime(java.time.Clock.systemUTC().instant());
        return Optional.of(courseDAO.save(course));
    }

    /**
     * Deletes course from Database
     *
     * @param id Course ID
     * @return CourseDTO
     */
    public CourseDTO deleteById(int id) {
//        studentDAO.deleteById(id);
        Course course = courseDAO.findById(id).get();

        CourseDTO courseDTO = courseMapper.mapFromCoursetoCourseDTO(course);
        courseDAO.deleteById(id);
        return courseDTO;
    }

    /**
     * Updates a course from Database by ID
     *
     * @param courseDTO
     * @param id Course ID
     * @return Optional<Course>
     */
    @Transactional
    public Optional<Course> update(CourseDTO courseDTO, int id) {
        CourseExists(courseDTO.getCourseCode());
        NumberOfStudentsInCourse(id);
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        course.setModifiedTime(java.time.Clock.systemUTC().instant());
        course.setId(id);
        return Optional.of(courseDAO.save(course));
    }

    /**
     * Checks courses if is there any duplicate course data
     *
     * @param courseCode Every course has courseCode. its name of them
     * @return void
     */
    private void CourseExists(int courseCode) {

        if (courseDAO.findCourseByCourseCode(courseCode).isPresent()) {
            throw new CourseIsAlreadyExistException("Course Is Already Exist!");
        }
    }

    /**
     * Checks courses if is there any duplicate course data
     *
     * @param numberOfStudents Total number of student on a course
     * @return void
     */
    private void NumberOfStudentsInCourse(int numberOfStudents) {
        if (numberOfStudents > 20) {
            throw new StudentNumberForOneCourseExceededException("This course is limited to 20 people!");
        }
    }

}
