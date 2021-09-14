package dev.patika.homework.model;

import dev.patika.homework.model.enumaration.SalaryUpdateType;
import dev.patika.homework.util.ClientRequestInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SalaryLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int InstructorID;
    private double salaryBefore;
    private double salaryAfter;
    private int percentage;
    private LocalDate salaryChangeDate;
    private String clientIpAdress;
    private String clientURL;
    private String sessionActivityId;
    @Enumerated(EnumType.STRING)
    private SalaryUpdateType salaryUpdateType;

}
