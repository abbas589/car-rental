package edu.miu.carrental.domain.dto;

import edu.miu.carrental.domain.entity.Reservation;

/**
 * @author bazz
 * Apr 25 2023
 * 13:50
 */
public class ReservationDtoTransformer {

    public static ReservationDto getReservationDtoFromReservation(Reservation reservation) {
        return new ReservationDto(reservation.getLicensePlate(), reservation.getStartDate(), reservation.getEndDate());
    }
}
