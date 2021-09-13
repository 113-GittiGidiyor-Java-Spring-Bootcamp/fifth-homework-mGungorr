package dev.patika.homework.service;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.StudentAgeNotValidException;
import dev.patika.homework.mappers.StudentMapper;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.StudentDAO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This service has operations on api, you can do CRUD operations for Students and exceptions controls
 */
@Service
@RequiredArgsConstructor
public class StudentService{

    private final StudentDAO studentDAO;
    private final StudentMapper studentMapper;

    /**
     * Finds all Students on database and get them as List<StudentDTO>
     *
     * @return List<StudentDTO>
     */
    public List<StudentDTO> findAll() {
        List<StudentDTO> studentList = new ArrayList<>();
        Iterable<Student> studentIter = studentDAO.findAll();
        for (Student student : studentIter) {
            StudentDTO studentDTO = studentMapper.mapFromStudenttoStudentDTO(student);
            studentList.add(studentDTO);
        }

        return studentList;
    }

    /**
     * Finds Studenta by ID on database and get them as StudentDTO
     *
     * @param id id of the Student.
     * @return StudentDTO
     */
    public StudentDTO findById(int id) {
        return studentMapper.mapFromStudenttoStudentDTO(studentDAO.findById(id).get());
    }

    /**
     * Saves Students to Database
     *
     * @param studentDTO
     * @return Optional<StudentDTO>
     */
    @Transactional
    public Optional<Student> save (StudentDTO studentDTO) {
        calculateAgeFromBirthDate(studentDTO.getStudentBirthDate());

        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        student.setCreateTime(java.time.Clock.systemUTC().instant());
        student.setModifiedTime(java.time.Clock.systemUTC().instant());
        return Optional.of(studentDAO.save(student));
    }

    /**
     * Deletes Student from Database by ID
     *
     * @param id, Student ID
     * @return StudentDTO
     */
    public StudentDTO deleteById(int id) {
//        studentDAO.deleteById(id);
        Student student = studentDAO.findById(id).get();

        StudentDTO studentDTO = studentMapper.mapFromStudenttoStudentDTO(student);
        studentDAO.deleteById(id);
        return studentDTO;
    }

    /**
     * Updates a Student from Database by ID
     *
     * @param studentDTO
     * @param id id of the Student.
     * @return Optional<Student>
     */
    @Transactional
    public Optional<Student> update(StudentDTO studentDTO, int id) {

        calculateAgeFromBirthDate(studentDTO.getStudentBirthDate());
        Student student = studentMapper.mapFromStudentDTOtoStudent(studentDTO);
        student.setModifiedTime(java.time.Clock.systemUTC().instant());

        student.setId(id);
        return Optional.of(studentDAO.save(student));
    }

    /**
     * Takes birthdate as parameter and calculates age for age exception
     *
     * @param birthDate Student's birthdate
     * @return void
     */
    public void calculateAgeFromBirthDate(Date birthDate) {
        SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

        String birthYear = formatNowYear.format(birthDate);
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - Integer.parseInt(birthYear);
        if (age < 18 || age > 40) {
            throw new StudentAgeNotValidException("Student age is not between 18 and 40!!!");
        }
    }
}
