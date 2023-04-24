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
}
