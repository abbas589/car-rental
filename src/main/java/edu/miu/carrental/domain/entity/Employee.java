package edu.miu.carrental.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * @author bazz
 * Apr 25 2023
 * 14:26
 */
@Entity
public class Employee {
    @Id
    private Long id;

    private String employeeName;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
