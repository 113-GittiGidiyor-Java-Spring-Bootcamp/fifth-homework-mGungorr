package dev.patika.homework.repository;

import dev.patika.homework.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoggerDAO extends JpaRepository<Logger, Long> {

    List<Logger> findByStatusCodeContainingAndTimestampBetween(String statusCode, LocalDateTime from, LocalDateTime to);

    List<Logger> findByStatusCodeContaining(String statusCode);
}
