package edu.miu.carrental.respository;

import edu.miu.carrental.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bazz
 * Apr 25 2023
 * 14:39
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
