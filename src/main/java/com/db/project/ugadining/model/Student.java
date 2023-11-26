package com.db.project.ugadining.model;

import com.db.project.ugadining.model.enums.Allergy;
import com.db.project.ugadining.model.enums.FoodPreference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence_generator")
    @SequenceGenerator(
            name = "student_sequence_generator",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    private Long studentId;
    private String studentName;
    private String studentPhoneNumber;
    private String studentEmail;

    @ElementCollection
    @CollectionTable(name = "student_food_preferences", joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    private List<FoodPreference> studentFoodPreferences;

    @ElementCollection
    @CollectionTable(name = "student_allergies", joinColumns = @JoinColumn(name = "student_id"))
    @Enumerated(EnumType.STRING)
    private List<Allergy> studentAllergies;

    @ManyToOne
    @JoinColumn(name = "student_has_meal_plan")
    private MealPlan studentMealPlan;

}
