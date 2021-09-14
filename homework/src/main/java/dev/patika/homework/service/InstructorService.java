package dev.patika.homework.service;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.dto.SalaryDTO;
import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.exceptions.CourseIsAlreadyExistException;
import dev.patika.homework.exceptions.InstructorIsAlreadyExistException;
import dev.patika.homework.mappers.InstructorMapper;
import dev.patika.homework.model.*;
import dev.patika.homework.model.enumaration.SalaryUpdateType;
import dev.patika.homework.repository.InstructorDAO;
import dev.patika.homework.repository.SalaryLogDAO;
import dev.patika.homework.util.ClientRequestInfo;
import dev.patika.homework.util.SalaryValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * This service has operations on api, you can do CRUD operations for Instructors and exceptions controls
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorDAO instructorDAO;
    private final InstructorMapper instructorMapper;
    private final ClientRequestInfo clientRequestInfo;
    private final SalaryLogDAO salaryLogDAO;

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

    public Instructor updateSalary(int id, SalaryDTO salaryDTO) {
        Instructor instructor =  instructorDAO.findById(id).get();
        double salaryAfter = 0;
        double currentSalary = 0;
        if (instructor instanceof VisitingResearcher){
            currentSalary = ((VisitingResearcher) instructor).getHourlySalary();
            double salaryChangeAmount = (salaryDTO.getPercentage()/100)*currentSalary;
            if (salaryDTO.getSalaryUpdateType() == SalaryDTO.SalaryUpdateType.INCREASE){
                salaryAfter = currentSalary + salaryChangeAmount;
            }
            else {
                salaryAfter = currentSalary - salaryChangeAmount;
            }
            ((VisitingResearcher) instructor).setHourlySalary(salaryAfter);
        }
        else if (instructor instanceof PermanentInstructor){
            currentSalary = ((PermanentInstructor) instructor).getFixedSalary();
            double salaryChangeAmount = (salaryDTO.getPercentage()/100)*currentSalary;
            if (salaryDTO.getSalaryUpdateType() == SalaryDTO.SalaryUpdateType.INCREASE){
                salaryAfter = currentSalary + salaryChangeAmount;
            }
            else {
                salaryAfter = currentSalary - salaryChangeAmount;
            }
            ((PermanentInstructor) instructor).setFixedSalary(salaryAfter);
        }
        SalaryLogger salaryLogger = new SalaryLogger();
        salaryLogger.setInstructorID(instructor.getId());
        salaryLogger.setSalaryAfter(salaryAfter);
        salaryLogger.setSalaryBefore(currentSalary);
        salaryLogger.setSalaryChangeDate(LocalDate.now());
        salaryLogger.setClientURL(clientRequestInfo.getClientURL());
        salaryLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        salaryLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        salaryLogger.setPercentage(salaryDTO.getPercentage());
        this.salaryLogDAO.save(salaryLogger);
        return instructor;
    }

    public Page<List<SalaryLogger>> getAllTransactionsWithDate(String changedDate, Pageable pageable) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        SalaryValidatorUtil.validateSalary(changedDate,formatter);
        LocalDate changedDateResult = LocalDate.parse(changedDate,formatter);
        return this.salaryLogDAO.findAllSalaryByChangeDate(changedDateResult,pageable);
    }
}
