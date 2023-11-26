package com.db.project.ugadining.model.dto;

import com.db.project.ugadining.model.Student;
import com.db.project.ugadining.model.enums.Allergy;
import com.db.project.ugadining.model.enums.FoodPreference;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Student}
 */
public record StudentDto(
        @Nullable Long studentId,
        @Nullable String studentName,
        @Nullable String studentPhoneNumber,
        @Nullable String studentEmail,
        @Nullable List<FoodPreference> studentFoodPreferences,
        @Nullable List<Allergy> studentAllergies,
        @Nullable Long studentMealPlanId
) implements Serializable {
}