package com.db.project.ugadining.model.dto;

import com.db.project.ugadining.model.enums.FoodPreference;
import jakarta.annotation.Nullable;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.db.project.ugadining.model.Dish}
 */
public record DishDto(
        @Nullable String dishName,
        @Nullable String dishIngredients,
        @Nullable FoodPreference dishType,
        @Nullable List<String> menuList
) implements Serializable {
}