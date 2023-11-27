package com.db.project.ugadining.model;

import com.db.project.ugadining.model.enums.FoodPreference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "dish")
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    private String dishName;

    private String dishIngredients;

    @Enumerated(EnumType.STRING)
    private FoodPreference dishType;

    @ManyToMany(mappedBy = "dishList")
    @ToString.Exclude
    @JsonBackReference
    private List<Menu> menuList = new ArrayList<>();

}
