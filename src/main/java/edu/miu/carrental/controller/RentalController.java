package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.RentalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bazz
 * Apr 24 2023
 * 18:28
 */
@RestController
@RequestMapping("rental")
public class RentalController {

    @PostMapping("/pickup")
    public ResponseEntity<String> pickupCar(@RequestBody RentalDto rentalDTO) {
        // Check if the car is available for pickup
//        if (!carService.isCarAvailable(rentalDTO.getCarType(), rentalDTO.getPickupDate(), rentalDTO.getReturnDate())) {
//            return ResponseEntity.badRequest().body("The car is not available for pickup at the given date.");
//        }
        return null;

    }
}
