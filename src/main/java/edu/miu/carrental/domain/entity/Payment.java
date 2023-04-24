package edu.miu.carrental.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

/**
 * @author bazz
 * Apr 23 2023
 * 20:45
 */
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    private String creditCardNumber;
    private String cardCvv;
    @ManyToOne
    private CarRental rental;
    private BigDecimal amountPaid;
    private String paymentType;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
