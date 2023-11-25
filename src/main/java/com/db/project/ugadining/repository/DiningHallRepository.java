package com.db.project.ugadining.repository;

import com.db.project.ugadining.model.DiningHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiningHallRepository extends JpaRepository<DiningHall, String> {

    Optional<DiningHall> findDiningHallByDiningHallName(String diningHallName);

}
