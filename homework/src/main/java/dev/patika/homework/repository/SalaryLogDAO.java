package dev.patika.homework.repository;

import dev.patika.homework.model.SalaryLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryLogDAO extends PagingAndSortingRepository<SalaryLogger,Integer> {
    @Query("SELECT s FROM SalaryLogger s WHERE s.salaryChangeDate = ?1")
    Page<List<SalaryLogger>> findAllSalaryByChangeDate(LocalDate changedDate, Pageable pageable);
}