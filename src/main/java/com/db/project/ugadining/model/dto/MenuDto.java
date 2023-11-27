package com.db.project.ugadining.model.dto;

import jakarta.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.db.project.ugadining.model.Menu}
 */
public record MenuDto(
        @Nullable String menuName,
        @Nullable List<DishDto> dishList,
        @Nullable String diningHallName
) implements Serializable {
}