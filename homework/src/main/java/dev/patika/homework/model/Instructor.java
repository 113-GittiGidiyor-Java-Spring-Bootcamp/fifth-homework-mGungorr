package dev.patika.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Instructor extends AbstractBaseEntity{

    private String instructorName;
    private String instructorAdress;
    private long instructorPhone;

    @OneToMany(mappedBy = "instructor")
    @ToString.Exclude
    private Set<Course> courseSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return instructorPhone == that.instructorPhone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorPhone);
    }
}
