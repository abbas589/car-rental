package edu.miu.carrental.domain.dto;

import edu.miu.carrental.domain.entity.Customer;
import edu.miu.carrental.respository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bazz
 * Apr 25 2023
 * 10:20
 */
@Service
public class CustomerDtoTransformer {

    @Autowired
    ReservationRepository reservationRepository;

    public CustomerDto getCustomerDtoFromCustomer(Customer customer) {
        return new CustomerDto(customer.getCustomerNumber(), customer.getName(), customer.getEmail(), reservationRepository.findAllByCustomer(customer));
    }

//    public static Customer getCustomerFromCustomerDto(CustomerDto dto){
//        return new Customer(dto.getCustomerNumber(),dto.getName(),dto.getEmail(),dto.getReservations(),dto.getCarRentals());
//    }

    public CustomersDto getCustomersDtoFromCustomers(List<Customer> customerList) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerList.forEach(v -> {
            customerDtos.add(getCustomerDtoFromCustomer(v));
        });
        return new CustomersDto(customerDtos);
    }
}
