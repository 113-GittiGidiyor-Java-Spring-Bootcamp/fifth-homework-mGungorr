package dev.patika.homework.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CourseDTO {

    private int id;
    private String courseName;
    private int courseCode;
    private int courseCreditPoint;
    private Instant createTime;
    private Instant modifiedTime;
}
