package com.db.project.ugadining.model;

import com.db.project.ugadining.model.enums.Designation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long staffId;
    private String staffName;
    @Enumerated(EnumType.STRING)
    private Designation staffDesignation;
    private String staffPhoneNumber;
    private String staffEmail;
    private BigDecimal staffSalary;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime workStartTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime workEndTime;
    @ManyToOne
    @JoinColumn(name = "works_in_dining_hall")
    private DiningHall worksIn;

}
