package dev.patika.homework.mappers;

import dev.patika.homework.dto.InstructorDTO;
import dev.patika.homework.model.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class InstructorMapper {
    public abstract Instructor mapFromInstructorDTOtoInstructor(InstructorDTO dto);
    public abstract InstructorDTO mapFromInstructortoInstructorDTO(Instructor instructor);
}
