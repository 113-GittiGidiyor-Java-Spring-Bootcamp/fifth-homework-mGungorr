package dev.patika.homework.model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Data
@Entity
@MappedSuperclass
public class VisitingResearcher extends Instructor{
    private double hourlySalary;
}
