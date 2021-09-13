package dev.patika.homework.exceptions;

public class StudentNumberForOneCourseExceededException extends RuntimeException{
    public StudentNumberForOneCourseExceededException(String message) {
        super(message);
    }
}
