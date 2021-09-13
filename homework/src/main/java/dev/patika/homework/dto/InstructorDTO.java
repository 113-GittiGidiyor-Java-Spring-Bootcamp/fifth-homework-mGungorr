package dev.patika.homework.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InstructorDTO {

    private int id;
    private String instructorName;
    private String instructorAdress;
    private long instructorPhone;
    private Instant createTime;
    private Instant modifiedTime;
}
