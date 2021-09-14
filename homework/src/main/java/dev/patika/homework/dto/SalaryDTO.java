package dev.patika.homework.dto;

import dev.patika.homework.model.enumaration.SalaryUpdateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class SalaryDTO {
    @ApiModelProperty(notes = "Inputs: INCREASE , DECREASE", example = "INCREASE")
    @NotBlank
    private SalaryUpdateType salaryUpdateType;

    @ApiModelProperty(notes = "Enter an Integer percentage value", example = "50")
    private int percentage;
    public enum SalaryUpdateType{
        INCREASE,
        DECREASE
    }
}
