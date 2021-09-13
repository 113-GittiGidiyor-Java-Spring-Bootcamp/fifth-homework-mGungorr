package dev.patika.homework.mappers;

import dev.patika.homework.dto.StudentDTO;
import dev.patika.homework.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public abstract Student mapFromStudentDTOtoStudent(StudentDTO dto);
    public abstract StudentDTO mapFromStudenttoStudentDTO(Student student);
}
