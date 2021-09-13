package dev.patika.homework.mappers;

import dev.patika.homework.dto.CourseDTO;
import dev.patika.homework.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {
    public abstract Course mapFromCourseDTOtoCourse(CourseDTO dto);
    public abstract CourseDTO mapFromCoursetoCourseDTO(Course course);
}
