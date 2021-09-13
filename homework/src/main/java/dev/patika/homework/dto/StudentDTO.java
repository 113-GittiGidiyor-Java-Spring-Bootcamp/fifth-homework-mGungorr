package dev.patika.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.homework.model.enumaration.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "Mustafa")
    @NotBlank(message = "First name is mandatory!")
    private String studentName;

    @ApiModelProperty(example = "1997-03-18")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date studentBirthDate;

    @ApiModelProperty(example = "Eskişehir")
    @NotBlank(message = "Address is mandatory!")
    private String studentAdress;

    @ApiModelProperty(example = "MALE")
    @NotBlank(message = "Gender is mandatory!")
    private Gender gender;

    private Instant createTime;
    private Instant modifiedTime;

}
