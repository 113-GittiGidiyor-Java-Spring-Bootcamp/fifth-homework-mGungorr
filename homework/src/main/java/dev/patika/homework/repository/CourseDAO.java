package dev.patika.homework.repository;

import dev.patika.homework.model.Course;
import dev.patika.homework.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDAO extends CrudRepository<Course,Integer> {

    Optional<Course> findCourseByCourseCode(int courseCode);

}
