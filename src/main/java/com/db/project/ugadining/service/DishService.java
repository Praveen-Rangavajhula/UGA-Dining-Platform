package com.db.project.ugadining.service;

import com.db.project.ugadining.exception.AlreadyExistsException;
import com.db.project.ugadining.exception.NotFoundException;
import com.db.project.ugadining.exception.ServiceException;
import com.db.project.ugadining.model.Dish;
import com.db.project.ugadining.model.Menu;
import com.db.project.ugadining.model.dto.DishDto;
import com.db.project.ugadining.repository.DishRepository;
import com.db.project.ugadining.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishService {

    private static final Logger logger = LoggerFactory.getLogger(DishService.class);
    private final DishRepository dishRepository;
    private final MenuRepository menuRepository;

    public List<Dish> getDishes() {
        try {
            logger.info("Obtaining all dishes");
            return dishRepository.findAll();
        } catch (RuntimeException e) {
            throw new ServiceException("Error occurred while obtaining all dishes", e);
        }
    }

    public List<Dish> getDishesByMenuName(String menuName) {
        try {
            logger.info("Obtaining dishes for menu {}", menuName);
            return dishRepository.findDishesByMenuList_MenuName(menuName);
        } catch (RuntimeException e) {
            throw new ServiceException("Error occurred while obtaining dishes for menu " + menuName, e);
        }
    }

    @Transactional
    public Dish putNewDish(DishDto dishDto) {
        if (dishDto.dishName() == null || dishDto.dishIngredients() == null || dishDto.dishType() == null || (dishDto.menuList() != null && dishDto.menuList().isEmpty())) {
            throw new IllegalArgumentException("Required fields are missing in DishDto");
        }

        Dish dish = Dish.builder()
                .dishName(dishDto.dishName())
                .dishIngredients(dishDto.dishIngredients())
                .dishType(dishDto.dishType())
                .build();

        try {
            logger.info("Registering a new dish {}", dish.getDishName());

            Optional<Dish> existingDish = dishRepository.findById(dish.getDishName());
            if(existingDish.isPresent()) {
                throw new AlreadyExistsException(dish.getDishName());
            }

            dishRepository.save(dish);

            if (dishDto.menuList() != null && !dishDto.menuList().isEmpty()) {

                List<Menu> menus = menuRepository.findAllByMenuNameIn(dishDto.menuList());

                for (Menu menu : menus) {
                    menu.getDishList().add(dish);
                    menuRepository.save(menu);
                }
            }

            return dish;
        } catch (DataAccessException e) {
            throw new ServiceException("Error registering a new dish", e);
        }
    }


    public void deleteDishByName(String dishName) {
        try {
            logger.info("Deleting the dish with name {}", dishName);
            Dish dish = dishRepository.findById(dishName)
                    .orElseThrow(() -> new NotFoundException(String.format("Dish with name %s does not exist", dishName)));

            // Remove the dish from the menuList of associated menus
            for (Menu menu : dish.getMenuList()) {
                menu.getDishList().removeIf(d -> d.getDishName().equals(dishName));
                menuRepository.save(menu);
            }

            // Delete the dish
            dishRepository.deleteById(dishName);
        } catch (DataAccessException e) {
            throw new ServiceException("Error deleting dish", e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Could not find dish" + e);
        }
    }


    @Transactional
    public Dish updateDishByName(String dishName, DishDto dishDto) {
        try {
            logger.info("Updating the dish: {}", dishName);
            Dish existingDish = dishRepository.findById(dishName)
                    .orElseThrow(() -> new NotFoundException(String.format("Dish with name %s not found", dishName)));

            if (dishDto.dishName() != null) {
                existingDish.setDishName(dishDto.dishName());
            }

            if (dishDto.dishIngredients() != null) {
                existingDish.setDishIngredients(dishDto.dishIngredients());
            }

            if (dishDto.dishType() != null) {
                existingDish.setDishType(dishDto.dishType());
            }

            Dish updatedDish = dishRepository.save(existingDish);

            // Update the associated menus
            updateMenusForDish(updatedDish, dishDto.menuList());

            return updatedDish;
        } catch (DataAccessException e) {
            throw new ServiceException("Error updating dish", e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Could not find dish" + e);
        }
    }

    private void updateMenusForDish(Dish dish, List<String> menuList) {
        if (menuList != null) {
            List<Menu> menus = menuRepository.findAllByMenuNameIn(menuList);

            for (Menu menu : menus) {
               // Check if the menu already has the dish name
                boolean dishExistsInMenu = menu.getDishList().stream()
                        .anyMatch(d -> d.getDishName().equals(dish.getDishName()));
                if (!dishExistsInMenu) {
                    menu.getDishList().add(dish);
                }

                menuRepository.save(menu);
            }

            // If not already in the list then update
            menuRepository.findAll().forEach(menu -> {
                if (!menuList.contains(menu.getMenuName()) && menu.getDishList().contains(dish)) {
                    menu.getDishList().remove(dish);
                    menuRepository.save(menu);
                }
            });
        }
    }


}