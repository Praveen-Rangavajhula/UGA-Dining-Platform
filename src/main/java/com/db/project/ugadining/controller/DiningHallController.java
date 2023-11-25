package com.db.project.ugadining.controller;

import com.db.project.ugadining.model.DiningHall;
import com.db.project.ugadining.model.dto.DiningHallDto;
import com.db.project.ugadining.service.DiningHallService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dining-hall")
@RequiredArgsConstructor
public class DiningHallController {

    private static final Logger logger = LoggerFactory.getLogger( DiningHallController.class );
    private final DiningHallService diningHallService;

    @GetMapping
    public List<DiningHall> getDiningHalls() {

        return diningHallService.getDiningHalls();
    }

    @GetMapping(path = "{diningName}")
    public DiningHall getDiningHallByName(@PathVariable String diningName) {

        return diningHallService.getDiningHallByName(diningName);
    }

    @PostMapping
    public ResponseEntity<DiningHall> postDiningHall(
            @RequestBody DiningHall diningHall
    ) {

        diningHallService.putNewDiningHall(diningHall);
        logger.info("Successfully registered dining hall {} ", diningHall.getDiningHallName());
        return new ResponseEntity<>(diningHall, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{diningName}")
    public ResponseEntity<String> deleteDiningHall (
            @PathVariable String diningName
    ) {
        diningHallService.deleteDiningHall(diningName);
        return ResponseEntity.ok("Deleted Dining Hall");
    }

    @PutMapping(path = "{diningName}")
    public ResponseEntity<DiningHall> updateDiningHall(
            @PathVariable String diningName,
            @RequestBody DiningHallDto diningHallDto
    ) {
        DiningHall diningHall = diningHallService.updateDiningHall(diningName, diningHallDto);
        return new ResponseEntity<>(diningHall, HttpStatus.OK);
    }

}
