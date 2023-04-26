package edu.miu.carrental.domain.dto;

import edu.miu.carrental.domain.entity.Reservation;

import java.util.List;

/**
 * @author bazz
 * Apr 26 2023
 * 14:51
 */
public class CarDataPojo {
    private CarDto car;
    private List<Reservation> reservations;

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public CarDataPojo(CarDto car, List<Reservation> reservations) {
        this.car = car;
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "CarDataPojo{" +
                "car=" + car +
                ", reservations=" + reservations +
                '}';
    }
    public CarDataPojo() {
    }
}
