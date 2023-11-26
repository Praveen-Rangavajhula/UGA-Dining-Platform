package com.db.project.ugadining.controller;

import com.db.project.ugadining.model.MealPlan;
import com.db.project.ugadining.model.dto.MealPlanDto;
import com.db.project.ugadining.service.MealPlanService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meal-plan")
@RequiredArgsConstructor
public class MealPlanController {

    private static final Logger logger = LoggerFactory.getLogger( MealPlanController.class );
    private final MealPlanService mealPlanService;

    @GetMapping
    public List<MealPlan> getMealPlans() {

        return mealPlanService.getMealPlans();
    }

    @GetMapping(path = "{mealPlanId}")
    public MealPlan getMealPlanById(@PathVariable Long mealPlanId) {

        return mealPlanService.getMealPlanById(mealPlanId);
    }

    @PostMapping
    public ResponseEntity<MealPlan> postMealPlan(
            @RequestBody MealPlanDto mealPlanDto
    ) {

        MealPlan mealPlan = mealPlanService.putNewMealPlan(mealPlanDto);
        logger.info("Successfully registered mealPlan");
        return new ResponseEntity<>(mealPlan, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{mealPlanId}")
    public ResponseEntity<String> deleteMealPlanById (
            @PathVariable Long mealPlanId
    ) {
        mealPlanService.deleteById(mealPlanId);
        return ResponseEntity.ok("Deleted Meal Plan with ID: " + mealPlanId);
    }

    @PutMapping(path = "{mealPlanId}")
    public ResponseEntity<MealPlan> updateMealPlan(
            @PathVariable Long mealPlanId,
            @RequestBody MealPlanDto mealPlanDto
    ) {
        MealPlan mealPlan = mealPlanService.updateMealPlan(mealPlanId, mealPlanDto);
        return new ResponseEntity<>(mealPlan, HttpStatus.OK);
    }

}
