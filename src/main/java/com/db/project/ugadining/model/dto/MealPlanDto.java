package com.db.project.ugadining.model.dto;

import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.db.project.ugadining.model.MealPlan}
 */
public record MealPlanDto(
        @Nullable Long mealPlanId,
        @Nullable String mealPlanDuration,
        @Nullable BigDecimal mealPlanPrice
) implements Serializable {
}