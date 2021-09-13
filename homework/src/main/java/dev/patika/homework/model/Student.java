package dev.patika.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.homework.model.enumaration.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Student extends AbstractBaseEntity{

    private String studentName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date studentBirthDate;
    private String studentAdress;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<Course> studentCourses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentName, student.studentName) && Objects.equals(studentBirthDate, student.studentBirthDate) && Objects.equals(studentAdress, student.studentAdress) && gender == student.gender && Objects.equals(studentCourses, student.studentCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentBirthDate, studentAdress, gender, studentCourses);
    }
}
