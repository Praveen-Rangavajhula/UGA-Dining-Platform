package com.db.project.ugadining.service;

import com.db.project.ugadining.exception.NotFoundException;
import com.db.project.ugadining.exception.ServiceException;
import com.db.project.ugadining.model.DiningHall;
import com.db.project.ugadining.model.Dish;
import com.db.project.ugadining.model.Menu;
import com.db.project.ugadining.model.dto.DishDto;
import com.db.project.ugadining.model.dto.MenuDto;
import com.db.project.ugadining.repository.DiningHallRepository;
import com.db.project.ugadining.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private final MenuRepository menuRepository;
    private final DiningHallRepository diningHallRepository;

    public List<Menu> getMenus() {
        try {
            logger.info("Obtaining all Menus");
            return menuRepository.findAll();
        } catch (RuntimeException e) {
            throw new ServiceException("Error occurred while obtaining all Menus", e);
        }
    }

    public List<Menu> getMenusByDiningHallName(String diningHallName) {
        try {
            logger.info("Obtaining all Menus available in the dining hall {}.", diningHallName);
            return menuRepository.findMenusByDiningHallDiningHallName(diningHallName);
        } catch (RuntimeException e) {
            throw new ServiceException("Error occurred while obtaining Menus for dining hall " + diningHallName, e);
        }
    }

    public Menu putNewMenu(MenuDto menuDto) {
        if (menuDto.menuName() == null || menuDto.diningHallName() == null || menuDto.dishList() == null) {
            throw new IllegalArgumentException("Required fields are missing in MenuDto");
        }

        DiningHall diningHall = diningHallRepository.findDiningHallByDiningHallName(menuDto.diningHallName())
                .orElseThrow(() -> new RuntimeException("DiningHall not found"));

        List<Dish> dishes = menuDto.dishList().stream()
                .map(this::convertDishDtoToDish)
                .toList();

        Menu menu = Menu.builder()
                .menuName(menuDto.menuName())
                .dishList(dishes)
                .diningHall(diningHall)
                .build();

        logger.info("Adding a new menu: {}", menu);
        logger.info("Dishes for the menu: {}", dishes);

        return menuRepository.save(menu);
    }

    public void deleteMenuByName(String menuName) {
        logger.info("Deleting the menu with name {}", menuName);

        boolean exists = menuRepository.existsByMenuName(menuName);
        if (!exists) {
            logger.info("Menu with name {} does not exist", menuName);
            throw new NotFoundException(String.format("Menu with name %s does not exist", menuName));
        }

        menuRepository.deleteByMenuName(menuName);
    }

    @Transactional
    public Menu updateMenuByName(String menuName, MenuDto menuDto) {
        try {
            logger.info("Updating the menu: {}", menuName);
            Menu existingMenu = menuRepository.findMenuByMenuName(menuName)
                    .orElseThrow(
                            () -> new NotFoundException(String.format("Menu with name %s not found", menuName))
                    );

            if (menuDto.menuName() != null) {
                existingMenu.setMenuName(menuDto.menuName());
            }

            if (menuDto.dishList() != null) {
                List<Dish> updatedDishList = menuDto.dishList().stream()
                        .map(this::convertDishDtoToDish)
                        .toList();
                existingMenu.setDishList(updatedDishList);
            }

            if (menuDto.diningHallName() != null) {
                DiningHall diningHall = diningHallRepository.findDiningHallByDiningHallName(menuDto.diningHallName())
                        .orElseThrow(
                                () -> new NotFoundException("DiningHall not found")
                        );

                existingMenu.setDiningHall(diningHall);
            }

            return existingMenu;

        } catch (DataAccessException e) {
            throw new ServiceException("Error updating menu", e);
        } catch (NotFoundException e) {
            throw new NotFoundException("Could not find menu" + e);
        }
    }

    private Dish convertDishDtoToDish(DishDto dishDto) {
        return Dish.builder()
                .dishName(dishDto.dishName())
                .dishType(dishDto.dishType())
                .dishIngredients(dishDto.dishIngredients())
                .build();
    }
}
