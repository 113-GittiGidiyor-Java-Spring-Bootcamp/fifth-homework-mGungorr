package dev.patika.homework.service;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.CourseIsAlreadyExistException;
import dev.patika.homework.exceptions.InstructorIsAlreadyExistException;
import dev.patika.homework.mappers.InstructorMapper;
import dev.patika.homework.model.Instructor;
import dev.patika.homework.model.Student;
import dev.patika.homework.repository.InstructorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This service has operations on api, you can do CRUD operations for Instructors and exceptions controls
 */
@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorDAO instructorDAO;
    private final InstructorMapper instructorMapper;

    /**
     * Finds all Instructor on database and get them as List<InstructorDTO>
     *
     * @return List<InstructorDTO>
     */
    public List<InstructorDTO> findAll() {
        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        Iterable<Instructor> instructorIter = instructorDAO.findAll();
        for (Instructor instructor : instructorIter) {
            InstructorDTO instructorDTO = instructorMapper.mapFromInstructortoInstructorDTO(instructor);
            instructorDTOList.add(instructorDTO);
        }

        return instructorDTOList;
    }

    /**
     * Finds Instructors by ID on database and get them as InstructorDTO
     *
     * @param id id of the Instructor.
     * @return InstructorDTO
     */
    public InstructorDTO findById(int id) {
        return instructorMapper.mapFromInstructortoInstructorDTO(instructorDAO.findById(id).get());
    }

    /**
     * Saves Instructors to Database
     *
     * @param instructorDTO
     * @return Optional<Instructor>
     */
    @Transactional
    public Optional<Instructor> save(InstructorDTO instructorDTO) {
        InstructorExists(instructorDTO.getInstructorPhone());
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        instructor.setCreateTime(java.time.Clock.systemUTC().instant());
        instructor.setModifiedTime(java.time.Clock.systemUTC().instant());
        return Optional.of(instructorDAO.save(instructor));
    }

    /**
     * Deletes Instructor from Database
     *
     * @param id, instructor ID
     * @return InstructorDTO
     */
    public InstructorDTO deleteById(int id) {
        Instructor instructor = instructorDAO.findById(id).get();

        InstructorDTO instructorDTO = instructorMapper.mapFromInstructortoInstructorDTO(instructor);
        instructorDAO.deleteById(id);
        return instructorDTO;
    }

    /**
     * Updates an Instructor from Database by ID
     *
     * @param instructorDTO
     * @param id id of the Instructor.
     * @return Optional<Instructor>
     */
    @Transactional
    public Optional<Instructor> update(InstructorDTO instructorDTO, int id) {
        InstructorExists(instructorDTO.getInstructorPhone());
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        instructor.setModifiedTime(java.time.Clock.systemUTC().instant());
        instructor.setId(id);
        return Optional.of(instructorDAO.save(instructor));
    }

    /**
     * Checks Instructors if is there any duplicate Instructor data
     *
     * @param instructorPhone Instructor phone Number, Each phone number should be different
     * @return void
     */
    private void InstructorExists(long instructorPhone) {

        if (instructorDAO.findInstructorByInstructorPhone(instructorPhone).isPresent()) {
            throw new InstructorIsAlreadyExistException("Instructor Is Already Exist With Same Phone Number!");
        }
    }
}
