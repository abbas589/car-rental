package edu.miu.carrental.service;

import java.math.BigDecimal;

import com.google.gson.Gson;
import edu.miu.carrental.AppConfiguration;
import edu.miu.carrental.domain.dto.*;
import edu.miu.carrental.domain.entity.Customer;

import java.time.LocalDate;

import edu.miu.carrental.CarFleetClient;
import edu.miu.carrental.domain.entity.Payment;
import edu.miu.carrental.domain.entity.Reservation;
import edu.miu.carrental.domain.enums.ReservationStatus;
import edu.miu.carrental.respository.CustomerRepository;
import edu.miu.carrental.respository.PaymentRepository;
import edu.miu.carrental.respository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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

    @Autowired
    AppConfiguration appConfiguration;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Gson gson;

    Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Transactional
    public ReservationDto reserveCar(ReservationRequestDto dto) {

        logger.info("Reservation search for car by type {} ==== ", dto.getCarType());

        CarsDto carsDto = client.searchCarFromFleet("type", dto.getCarType());

        logger.info("Car response from car DTO ======== {}", gson.toJson(carsDto));

        Optional<CarDto> optionalCarDto = carsDto.getCars().stream().filter(CarDto::getAvailable).findAny();

        if (optionalCarDto.isPresent()) {
            CarDto reserveCarInFleet = client.reserveCarInFleet(optionalCarDto.get().getLicensePlate());

            Reservation reservation = new Reservation();
            reservation.setLicensePlate(reserveCarInFleet.getLicensePlate());
            reservation.setStartDate(dto.getStartDate());
            reservation.setEndDate(dto.getStartDate());
            reservation.setReservationStatus(ReservationStatus.RESERVED);
            Customer customer = customerRepository.findById(dto.getCustomerNumber()).get();
            reservation.setCustomer(customer);
            reservationRepository.save(reservation);

            logger.info("================= About to send message :: {} to queue :: {}", reservation.getLicensePlate(), appConfiguration.getReservationQueue());
            String licensePlate = reservation.getLicensePlate();
            jmsTemplate.convertAndSend("reserve-car", licensePlate);

            return ReservationDtoTransformer.getReservationDtoFromReservation(reservation);
        }
        return null;
    }

    public CarsDto searchCar(String type) {
        return client.searchCarFromFleet("type", type);
    }

    public CarDataPojo getCarData(String licenseplate){

        CarDto carsDto = client.searchCarFromFleet("licenseplate",licenseplate).getCars().stream().findAny().get();
        reservationRepository.findAllByLicensePlateIgnoreCase(licenseplate);
        return new CarDataPojo(carsDto,reservationRepository.findAllByLicensePlateIgnoreCase(licenseplate));
    }

    @Transactional
    public ReservationDto reservationPickup(String licensePlate, Long customerNumber) {
        Optional<Reservation> reservation = reservationRepository.findFirstByCustomer_CustomerNumberAndLicensePlate(customerNumber, licensePlate);

        if (reservation.isPresent()) {
            reservation.get().setPickupDate(LocalDate.now());

            reservation.get().setReservationStatus(ReservationStatus.PICKED_UP);
            return ReservationDtoTransformer.getReservationDtoFromReservation(reservationRepository.save(reservation.get()));
        }

        return null;
    }

    @Transactional
    public ReservationDto returnCar(String licensePlate, ReturnCarDto dto) {
        Optional<Reservation> reservation = reservationRepository.findFirstByCustomer_CustomerNumberAndLicensePlate(dto.getCustomerNumber(), licensePlate);

        if (reservation.isPresent()) {
            reservation.get().setReturnDate(LocalDate.now());
            reservation.get().setReservationStatus(ReservationStatus.RETURNED);
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
       return paymentRepository.save(payment);

    }

    public CarsDto searchCars(String searchBy, String value) {
        return client.searchCarFromFleet(searchBy, value);
    }
}
