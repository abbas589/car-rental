package edu.miu.carrental.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author bazz
 * Apr 23 2023
 * 20:44
 */
@Entity
public class CarRental {
    @Id
    @GeneratedValue
    private Long id;
    private String licensePlate;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal amount;

    @ManyToOne
    private Customer customer;

}
