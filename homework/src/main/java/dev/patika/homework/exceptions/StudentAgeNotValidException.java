package dev.patika.homework.exceptions;

public class StudentAgeNotValidException extends RuntimeException{
    public StudentAgeNotValidException(String message) {
        super(message);
    }

}
