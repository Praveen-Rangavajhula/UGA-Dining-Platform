package com.db.project.ugadining.service;

import com.db.project.ugadining.exception.NotFoundException;
import com.db.project.ugadining.model.DiningHall;
import com.db.project.ugadining.model.dto.DiningHallDto;
import com.db.project.ugadining.repository.DiningHallRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiningHallService {

    private static final Logger logger = LoggerFactory.getLogger(DiningHallService.class);
    private final DiningHallRepository diningHallRepository;

    public List<DiningHall> getDiningHalls() {
        logger.info("Obtaining all dining halls");
        return diningHallRepository.findAll();
    }

    public DiningHall getDiningHallByName(String diningName) {
        logger.info("Obtaining dining hall with name {}", diningName);

        return diningHallRepository.findDiningHallByDiningHallName(diningName)
                .orElseThrow(() -> new NotFoundException(String.format("Dining Hall with name '%s' not found", diningName)));
    }

    public DiningHall putNewDiningHall(DiningHallDto diningHallDto) {
        if (diningHallDto.diningHallName() == null ||
                diningHallDto.diningHallAddress() == null ||
                diningHallDto.diningHallOpeningTime() == null ||
                diningHallDto.diningHallClosingTime() == null ||
                diningHallDto.diningHallPhoneNumber() == null ||
                diningHallDto.diningHallEmail() == null) {
            throw new IllegalArgumentException("Required fields are missing in DiningHallDto");
        }

        DiningHall diningHall = DiningHall.builder()
                .diningHallName(diningHallDto.diningHallName())
                .diningHallAddress(diningHallDto.diningHallAddress())
                .diningHallOpeningTime(diningHallDto.diningHallOpeningTime())
                .diningHallClosingTime(diningHallDto.diningHallClosingTime())
                .diningHallPhoneNumber(diningHallDto.diningHallPhoneNumber())
                .diningHallEmail(diningHallDto.diningHallEmail())
                .build();

        logger.info("Registering a new dining hall with name {}", diningHall.getDiningHallName());

        Optional<DiningHall> diningHallFromRepository = diningHallRepository.findDiningHallByDiningHallName(diningHall.getDiningHallName());
        if (diningHallFromRepository.isPresent()) {
            throw new IllegalStateException(
                    String.format("Dining hall with name '%s' already exists", diningHall.getDiningHallName())
            );
        }
        diningHallRepository.save(diningHall);
        return diningHall;
    }

    public void deleteDiningHall(String diningName) {
        logger.info("Deleting the dining hall with name {}", diningName);

        boolean exists = diningHallRepository.existsById(diningName);
        if (!exists) {
            logger.error("Dining hall with name {} does not exist", diningName);
            throw new NotFoundException(String.format("Dining hall with name %s does not exist", diningName));
        }
        diningHallRepository.deleteById(diningName);
    }

    @Transactional
    public DiningHall updateDiningHall(String diningName, DiningHallDto diningHallDto) {
        logger.info("Updating the dining hall with name {}", diningName);

        DiningHall existingDiningHall = diningHallRepository.findDiningHallByDiningHallName(diningName)
                .orElseThrow(() -> new NotFoundException(String.format("Dining hall with name %s not found", diningName)));

        if (diningHallDto.diningHallName() != null) {
            existingDiningHall.setDiningHallName(diningHallDto.diningHallName());
        }

        if (diningHallDto.diningHallAddress() != null) {
            existingDiningHall.setDiningHallAddress(diningHallDto.diningHallAddress());
        }

        if (diningHallDto.diningHallOpeningTime() != null) {
            existingDiningHall.setDiningHallOpeningTime(diningHallDto.diningHallOpeningTime());
        }

        if (diningHallDto.diningHallClosingTime() != null) {
            existingDiningHall.setDiningHallClosingTime(diningHallDto.diningHallClosingTime());
        }

        if (diningHallDto.diningHallPhoneNumber() != null) {
            existingDiningHall.setDiningHallPhoneNumber(diningHallDto.diningHallPhoneNumber());
        }

        if (diningHallDto.diningHallEmail() != null) {
            existingDiningHall.setDiningHallEmail(diningHallDto.diningHallEmail());
        }

        return existingDiningHall;
    }
}