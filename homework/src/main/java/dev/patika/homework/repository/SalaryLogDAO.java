package dev.patika.homework.repository;

import dev.patika.homework.model.SalaryLogger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SalaryLogDAO extends PagingAndSortingRepository<SalaryLogger,Integer> {

//    Optional<SalaryLog> findByInstructorIdAndCreatedDate(Long instructorId, LocalDate date);
}