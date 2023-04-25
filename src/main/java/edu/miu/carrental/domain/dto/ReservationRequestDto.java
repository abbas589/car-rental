package edu.miu.carrental.domain.dto;

/**
 * @author bazz
 * Apr 24 2023
 * 18:24
 */
public class ReservationRequestDto {
    private String carType;
    private Long customerNumber;

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public ReservationRequestDto(String carType, Long customerNumber) {
        this.carType = carType;
        this.customerNumber = customerNumber;
    }
}
