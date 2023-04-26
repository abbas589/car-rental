package edu.miu.carrental.domain.dto;

import java.time.LocalDate;

/**
 * @author bazz
 * Apr 25 2023
 * 13:49
 */
public class ReservationDto {
    private String licensePlate;

    private LocalDate startDate;
    private LocalDate endDate;

    public ReservationDto(String licensePlate, LocalDate startDate, LocalDate endDate) {
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "licensePlate='" + licensePlate + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
