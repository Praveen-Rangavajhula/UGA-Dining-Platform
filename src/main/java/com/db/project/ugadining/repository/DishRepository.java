package com.db.project.ugadining.repository;

import com.db.project.ugadining.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, String> {
    List<Dish> findDishesByMenuList_MenuName(String menuName);
}