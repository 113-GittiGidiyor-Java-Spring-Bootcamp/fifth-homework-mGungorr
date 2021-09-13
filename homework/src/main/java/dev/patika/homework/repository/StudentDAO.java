package dev.patika.homework.repository;

import dev.patika.homework.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends CrudRepository<Student,Integer> {

}
