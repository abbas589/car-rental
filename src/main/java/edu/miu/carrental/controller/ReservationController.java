package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.CarsDto;
import edu.miu.carrental.domain.dto.RentalDto;
import edu.miu.carrental.domain.dto.ReservationDto;
import edu.miu.carrental.domain.dto.ReservationRequestDto;
import edu.miu.carrental.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author bazz
 * Apr 24 2023
 * 18:24
 */
@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> reserveCar(ReservationRequestDto dto){
        ReservationDto reservationDto = reservationService.reserveCar(dto);
        log.info("RESERVE CUSTOMER ENDPOINT CALLED ====");


        if(reservationDto == null){
            return new ResponseEntity<String>("This car Type is not available for reservation", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
    }
    @PostMapping("search")
    public ResponseEntity<?> searchCar(ReservationRequestDto dto){
        log.info("SEARCH CAR ENDPOINT CALLED ====");
        return new ResponseEntity<CarsDto>(reservationService.searchCar(dto), HttpStatus.OK);
    }

    @PostMapping("{licensePlate}/pickup/{customerNumber}")
    public ResponseEntity<?> pickupCar(@PathVariable("licensePlate") String licensePlate, @PathVariable("customerNumber") Long  customerNumber) {

        log.info("PICKUP CAR ENDPOINT CALLED ====");

        ReservationDto reservationDto = reservationService.reservationPickup(licensePlate, customerNumber);

        if(reservationDto == null){
            return new ResponseEntity<>("Reservation not found or already picked up", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservationDto,HttpStatus.OK);

    }
    @PostMapping("{licensePlate}/return/{customerNumber}")
    public ResponseEntity<?> returnCar(@PathVariable("licensePlate") String licensePlate, @PathVariable("customerNumber") Long  customerNumber) {

        ReservationDto reservationDto = reservationService.reservationPickup(licensePlate, customerNumber);

        if(reservationDto == null){
            return new ResponseEntity<>("Reservation not found or already picked up", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservationDto,HttpStatus.OK);

    }

}
