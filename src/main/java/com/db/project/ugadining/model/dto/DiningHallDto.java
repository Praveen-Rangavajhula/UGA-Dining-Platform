package com.db.project.ugadining.model.dto;

import com.db.project.ugadining.model.DiningHall;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * DTO for {@link DiningHall}
 */

public record DiningHallDto(
        @Nullable String diningHallName,
        @Nullable String diningHallAddress,
        @JsonFormat(pattern = "HH:mm") @Nullable LocalTime diningHallOpeningTime,
        @JsonFormat(pattern = "HH:mm") @Nullable LocalTime diningHallClosingTime,
        @Nullable String diningHallPhoneNumber,
        @Nullable String diningHallEmail
) implements Serializable {
}