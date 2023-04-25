package edu.miu.carrental.service;
import java.math.BigDecimal;
import edu.miu.carrental.domain.dto.*;
import edu.miu.carrental.domain.entity.Customer;
import java.time.LocalDate;

import edu.miu.carrental.CarFleetClient;
import edu.miu.carrental.domain.entity.Payment;
import edu.miu.carrental.domain.entity.Reservation;
import edu.miu.carrental.respository.CustomerRepository;
import edu.miu.carrental.respository.PaymentRepository;
import edu.miu.carrental.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author bazz
 * Apr 24 2023
 * 18:25
 */
@Service
public class ReservationService {

    @Autowired
    CarFleetClient client;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    public ReservationDto reserveCar(ReservationRequestDto dto){

        CarsDto carsDto = client.searchCarFromFleet("type", dto.getCarType());

        Optional<CarDto> optionalCarDto = carsDto.getCars().stream().filter(CarDto::getAvailable).findAny();
        if(optionalCarDto.isPresent()){
            CarDto reserveCarInFleet = client.reserveCarInFleet(optionalCarDto.get().getLicensePlate());

            Reservation reservation = new Reservation();
            reservation.setLicensePlate(reserveCarInFleet.getLicensePlate());
            reservation.setStartDate(LocalDate.now());
            reservation.setEndDate(LocalDate.now());
            Customer customer = customerRepository.findById(dto.getCustomerNumber()).get();
            reservation.setCustomer(customer);
            reservationRepository.save(reservation);

            return ReservationDtoTransformer.getReservationDtoFromReservation(reservation);
        }
        return null;
    }

    public CarsDto searchCar(ReservationRequestDto dto) {
        return client.searchCarFromFleet("type", dto.getCarType());
    }

    @Transactional
    public ReservationDto reservationPickup(String licensePlate, Long customerNumber) {
        Optional<Reservation> reservation = reservationRepository.findFirstByCustomer_CustomerNumberAndLicensePlate(customerNumber,licensePlate);

        if(reservation.isPresent()){
            reservation.get().setPickupDate(LocalDate.now());
            return ReservationDtoTransformer.getReservationDtoFromReservation(reservationRepository.save(reservation.get()));
        }

        return null;
    }
    @Transactional
    public ReservationDto returnCar(String licensePlate, ReturnCarDto dto) {
        Optional<Reservation> reservation = reservationRepository.findFirstByCustomer_CustomerNumberAndLicensePlate(dto.getCustomerNumber(),licensePlate);

        if(reservation.isPresent()){
            reservation.get().setReturnDate(LocalDate.now());
            Payment payment = createPayment(dto);
            reservation.get().setPayment(payment);
            return ReservationDtoTransformer.getReservationDtoFromReservation(reservationRepository.save(reservation.get()));
        }

        return null;
    }

    private Payment createPayment(ReturnCarDto dto) {
        Payment payment = new Payment();
        payment.setCreditCardNumber(dto.getCreditCardNumber());
        payment.setCardCvv(dto.getCardCvv());
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentType(dto.getPaymentType());
        payment.setCustomer(customerRepository.findById(dto.getCustomerNumber()).get());
        paymentRepository.save(payment);

    }
}
