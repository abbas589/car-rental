package edu.miu.carrental.respository;

import edu.miu.carrental.domain.entity.Customer;
import edu.miu.carrental.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author bazz
 * Apr 25 2023
 * 14:39
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByCustomer(Customer customer);
}
