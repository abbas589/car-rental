package edu.miu.carrental.respository;

import edu.miu.carrental.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bazz
 * Apr 25 2023
 * 13:42
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findFirstByCustomer_CustomerNumberAndLicensePlate(Long customerNumber, String licensePlate);
}
