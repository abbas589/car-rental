package edu.miu.carrental.domain.dto;

import edu.miu.carrental.domain.entity.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bazz
 * Apr 25 2023
 * 10:20
 */
public class CustomerDtoTransformer {

    public static CustomerDto getCustomersDtoFromCustomers(Customer customer) {
        return new CustomerDto(customer.getCustomerNumber(), customer.getName(), customer.getEmail());
    }

//    public static Customer getCustomerFromCustomerDto(CustomerDto dto){
//        return new Customer(dto.getCustomerNumber(),dto.getName(),dto.getEmail(),dto.getReservations(),dto.getCarRentals());
//    }

    public static CustomersDto getCustomersDtoFromCustomers(List<Customer> customerList) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerList.forEach(v -> {
            customerDtos.add(getCustomersDtoFromCustomers(v));
        });
        return new CustomersDto(customerDtos);
    }
}
