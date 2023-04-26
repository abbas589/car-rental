package edu.miu.carrental.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

/**
 * @author bazz
 * Apr 23 2023
 * 15:00
 */
@Entity
public class Customer {
    @Id
    private Long customerNumber;

    private String name;
    private String email;



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


    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
