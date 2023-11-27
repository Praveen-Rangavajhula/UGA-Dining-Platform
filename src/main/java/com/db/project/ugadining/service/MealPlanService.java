package com.db.project.ugadining.service;

import com.db.project.ugadining.exception.NotFoundException;
import com.db.project.ugadining.model.MealPlan;
import com.db.project.ugadining.model.dto.MealPlanDto;
import com.db.project.ugadining.repository.MealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MealPlanService {

    private static final Logger logger = LoggerFactory.getLogger( MealPlanService.class );
    private final MealPlanRepository mealPlanRepository;

    public List<MealPlan> getMealPlans() {
        logger.info("Obtaining all available meal plans");
        return mealPlanRepository.findAll();
    }

    public MealPlan getMealPlanById(Long id) {
        logger.info("Obtaining Meal Plan with id {}", id);
        return mealPlanRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Meal Plan with id '%d' not found", id))
                );
    }

    public MealPlan putNewMealPlan(MealPlanDto mealPlanDto) {
        MealPlan newMealPlan = MealPlan.builder()
                .mealPlanDuration(mealPlanDto.mealPlanDuration())
                .mealPlanPrice(mealPlanDto.mealPlanPrice())
                .build();

        logger.info("Registering a new meal plan: {}", newMealPlan);

        if (newMealPlan.getMealPlanDuration() == null || newMealPlan.getMealPlanPrice() == null) {
            throw new IllegalArgumentException("Meal plan duration and price must be specified.");
        }

        if (newMealPlan.getMealPlanPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Meal plan price cannot be negative.");
        }

        mealPlanRepository.save(newMealPlan);
        return newMealPlan;
    }

    public void deleteById(Long id) {
        logger.info("Deleting the meal plan with id {}", id);

        boolean exists = mealPlanRepository.existsById(id);
        if(!exists) {
            logger.error("Meal Plan with id {} does not exist", id);
            throw new NotFoundException(String.format("Meal Plan with id %s does not exist", id));
        }
        mealPlanRepository.deleteById(id);
    }

    @Transactional
    public MealPlan updateMealPlan(Long mealPlanId, MealPlanDto mealPlanDto) {
        logger.info("Updating the Meal plan with id {}", mealPlanId);

        MealPlan existingMealPlan = mealPlanRepository.findById(mealPlanId)
                .orElseThrow(() -> new NotFoundException(String.format("Student with name %d not found", mealPlanId)));


        if (mealPlanDto.mealPlanDuration() != null) {
            existingMealPlan.setMealPlanDuration(mealPlanDto.mealPlanDuration());
        }

        if (mealPlanDto.mealPlanPrice() != null) {
            existingMealPlan.setMealPlanPrice(mealPlanDto.mealPlanPrice());
        }

        return existingMealPlan;
    }
}