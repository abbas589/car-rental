package edu.miu.carrental.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

/**
 * @author bazz
 * Apr 23 2023
 * 15:01
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String licensePlate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private Customer customer;


    public Reservation(String licensePlate, LocalDateTime startDate, LocalDateTime endDate, Customer customer) {
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public Reservation() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
