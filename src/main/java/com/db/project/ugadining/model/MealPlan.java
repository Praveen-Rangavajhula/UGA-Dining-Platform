package com.db.project.ugadining.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "meal_plan")
@NoArgsConstructor
@AllArgsConstructor
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mealPlanId;
    private String mealPlanDuration;
    private BigDecimal mealPlanPrice;

}
