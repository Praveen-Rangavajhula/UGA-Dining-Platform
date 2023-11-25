package com.db.project.ugadining.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    private String menuName;

    @ManyToMany
    @JoinTable(
            name = "menu_dish_list",
            joinColumns = @JoinColumn(name = "menu_name"),
            inverseJoinColumns = @JoinColumn(name = "dish_name")
    )
    @ToString.Exclude
    private List<Dish> dishList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dining_hall_name")
    private DiningHall diningHall;

}
