package edu.miu.carrental.domain.dto;

import edu.miu.carrental.domain.entity.Reservation;

import java.util.List;

/**
 * @author bazz
 * Apr 24 2023
 * 18:14
 */
public class CustomerDto {
    private Long customerNumber;
    private String name;
    private String email;
    private List<Reservation> reservations;

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public CustomerDto(Long customerNumber, String name, String email) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
    }
    public CustomerDto(){}
}

