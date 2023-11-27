package com.db.project.ugadining.controller;

import com.db.project.ugadining.model.Menu;
import com.db.project.ugadining.model.dto.MenuDto;
import com.db.project.ugadining.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
@RequiredArgsConstructor
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    private final MenuService menuService;

    @GetMapping
    public List<Menu> getMenuList() {
        return menuService.getMenus();
    }

    @GetMapping("{diningHallName}")
    public List<Menu> getMenuByDiningHallName(@PathVariable String diningHallName) {
        return menuService.getMenusByDiningHallName(diningHallName);
    }

    @PostMapping
    public ResponseEntity<Menu> postMenu(@RequestBody MenuDto menuDto) {
        Menu menu = menuService.putNewMenu(menuDto);
        logger.info("Successfully added menu {}", menu.getMenuName());
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{menuName}")
    public ResponseEntity<String> deleteMenuByName(@PathVariable String menuName) {
        menuService.deleteMenuByName(menuName);
        return ResponseEntity.ok("Deleted menu with NAME: " + menuName);
    }

    @PutMapping(path = "{menuName}")
    public ResponseEntity<Menu> updateMenuByName(
            @PathVariable String menuName,
            @RequestBody MenuDto menuDto
    ) {
        Menu menu = menuService.updateMenuByName(menuName, menuDto);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
