package edu.miu.carrental.controller;

import com.google.gson.Gson;
import edu.miu.carrental.domain.dto.*;
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

    @Autowired
    Gson gson;

    @PostMapping("/create")
    public ResponseEntity<?> reserveCar(@RequestBody ReservationRequestDto dto){
        log.info("RESERVE CUSTOMER ENDPOINT CALLED ==== {} ", dto.getCarType());

        ReservationDto reservationDto = reservationService.reserveCar(dto);
        log.info("RESERVE CUSTOMER ENDPOINT CALLED ====");


        if(reservationDto == null){
            return new ResponseEntity<String>("This car Type is not available for reservation", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ReservationDto>(reservationDto, HttpStatus.OK);
    }
    @GetMapping("search")
    public ResponseEntity<?> searchCar(@RequestParam("type") String type){
        log.info("SEARCH CAR ENDPOINT CALLED ====");
        return new ResponseEntity<CarsDto>(reservationService.searchCar(type), HttpStatus.OK);
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
    @PostMapping("{licensePlate}/return")
    public ResponseEntity<?> returnCar(@PathVariable("licensePlate") String licensePlate, @RequestBody ReturnCarDto dto) {

        ReservationDto reservationDto = reservationService.returnCar(licensePlate, dto);

        if(reservationDto == null){
            return new ResponseEntity<>("Reservation not found or already returned", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservationDto,HttpStatus.OK);

    }

}
