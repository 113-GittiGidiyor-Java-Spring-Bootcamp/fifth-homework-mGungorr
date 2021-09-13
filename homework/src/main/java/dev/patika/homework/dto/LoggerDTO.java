package dev.patika.homework.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class LoggerDTO {

    @ApiModelProperty(hidden = true)
    private long id;

    private LocalDateTime timestamp;
    private String statusCode;
    private String message;
}
