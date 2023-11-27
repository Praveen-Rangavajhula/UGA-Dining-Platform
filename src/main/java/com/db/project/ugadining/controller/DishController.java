package com.db.project.ugadining.controller;


import com.db.project.ugadining.model.Dish;
import com.db.project.ugadining.model.dto.DishDto;
import com.db.project.ugadining.service.DishService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/dish")
@RequiredArgsConstructor
public class DishController {

    private static final Logger logger = LoggerFactory.getLogger(DishController.class);
    private final DishService dishService;

    @GetMapping
    public List<Dish> getDishList() {
        return dishService.getDishes();
    }

    @GetMapping("{menuName}")
    public List<Dish> getDishesByMenuName(@PathVariable String menuName) {
        return dishService.getDishesByMenuName(menuName);
    }


    @PostMapping
    public ResponseEntity<Dish> postDish(@RequestBody DishDto dishDto) {
        Dish dish = dishService.putNewDish(dishDto);
        logger.info("Successfully added dish {}", dish.getDishName());
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{dishName}")
    public ResponseEntity<String> deleteDishByName(@PathVariable String dishName) {
        dishService.deleteDishByName(dishName);
        return ResponseEntity.ok("Deleted dish with NAME: " + dishName);
    }

    @PutMapping(path = "{dishName}")
    public ResponseEntity<Dish> updateDishByName(
            @PathVariable String dishName,
            @RequestBody DishDto dishDto
    ) {
        Dish dish = dishService.updateDishByName(dishName, dishDto);
        return new ResponseEntity<>(dish, HttpStatus.OK);
    }
}
