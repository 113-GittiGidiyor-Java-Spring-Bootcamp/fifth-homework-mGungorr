package dev.patika.homework.util;

import dev.patika.homework.exceptions.ChangedTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryValidatorUtil {
    public static void validateSalary(String changedDate, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(changedDate, formatter);
        } catch (DateTimeParseException e) {
            throw new ChangedTimeParseException(ErrorMessageConstants.DATE_FORMAT_WRONG + changedDate);
        }
    }
}
