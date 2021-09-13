package dev.patika.homework.mappers;

import dev.patika.homework.dto.LoggerDTO;
import dev.patika.homework.model.Logger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class LoggerMapper {
    public abstract LoggerDTO mapFromLoggertoLoggerDTO(Logger logger);
    public abstract Logger mapFromLoggerDTOtoLogger(LoggerDTO loggerDTO);
}
