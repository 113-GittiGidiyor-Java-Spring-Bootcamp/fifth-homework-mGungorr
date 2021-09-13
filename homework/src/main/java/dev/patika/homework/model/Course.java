package dev.patika.homework.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Course extends AbstractBaseEntity{

    private String courseName;
    private int courseCode;
    private int courseCreditPoint;

    @ManyToMany
    @ToString.Exclude
    private Set<Student> student = new HashSet<>();

    @ManyToOne
    private Instructor instructor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseCode == course.courseCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
}
