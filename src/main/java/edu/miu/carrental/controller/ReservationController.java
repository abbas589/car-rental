package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.ReservationDto;
import edu.miu.carrental.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bazz
 * Apr 24 2023
 * 18:24
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> reserveCar(ReservationDto dto){
        return new ResponseEntity<ReservationDto>(reservationService.reserveCar(dto), HttpStatus.OK);
    }

}
