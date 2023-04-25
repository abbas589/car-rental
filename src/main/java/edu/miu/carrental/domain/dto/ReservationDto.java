package edu.miu.carrental.domain.dto;

/**
 * @author bazz
 * Apr 24 2023
 * 18:24
 */
public class ReservationDto {
    private String carType;

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public ReservationDto(String carType) {
        this.carType = carType;
    }
}
