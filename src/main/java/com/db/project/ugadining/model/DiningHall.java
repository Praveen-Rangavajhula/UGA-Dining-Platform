package com.db.project.ugadining.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "dining_hall")
@NoArgsConstructor
@AllArgsConstructor
public class DiningHall {

    @Id
    private String diningHallName;
    private String diningHallAddress;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime diningHallOpeningTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime diningHallClosingTime;
    private String diningHallPhoneNumber;
    private String diningHallEmail;

}