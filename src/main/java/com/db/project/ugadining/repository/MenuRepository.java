package com.db.project.ugadining.repository;

import com.db.project.ugadining.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findMenusByDiningHallDiningHallName(String diningHallName);

    Optional<Menu> findMenuByMenuName(String menuName);

    boolean existsByMenuName(String menuName);

    void deleteByMenuName(String menuName);

    List<Menu> findAllByMenuNameIn(List<String> strings);
}