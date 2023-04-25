package edu.miu.carrental.respository;

import edu.miu.carrental.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bazz
 * Apr 25 2023
 * 10:19
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByCustomerNumber(String customerNumber);

    List<Customer> findAllByNameIgnoreCase(String name);

    List<Customer> findAllByEmailIgnoreCase(String email);

}
