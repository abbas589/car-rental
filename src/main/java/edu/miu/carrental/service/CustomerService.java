package edu.miu.carrental.service;

import edu.miu.carrental.domain.dto.CustomerDataDto;
import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.domain.dto.CustomerDtoTransformer;
import edu.miu.carrental.domain.dto.CustomersDto;
import edu.miu.carrental.domain.entity.Customer;
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
 * 18:07
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerDtoTransformer customerDtoTransformer;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PaymentRepository paymentRepository;


    public CustomersDto getAllCustomers(){
        return customerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAll());
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto dto){
        Customer customer = new Customer();
        customer.setCustomerNumber(dto.getCustomerNumber());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return customerDtoTransformer.getCustomerDtoFromCustomer(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(Long customerNumber, CustomerDto dto){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerNumber);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setEmail(dto.getEmail());
            customer.setName(dto.getName());
           return customerDtoTransformer.getCustomerDtoFromCustomer(customerRepository.save(customer));
        }
        return null;
    }

    public boolean deleteCustomer(Long customerNumber){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerNumber);

        if(optionalCustomer.isPresent()){
            customerRepository.delete(optionalCustomer.get());
            return true;
        }

        return false;
    }

    public CustomersDto searchCustomer(String searchBy, String value) {
        CustomersDto customersDto = new CustomersDto();
        switch (searchBy){
            case "customernumber":
                return customerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByCustomerNumber(value));
            case "name":
                return customerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByNameIgnoreCase(value));
            case "email":
                return customerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByEmailIgnoreCase(value));
        }
        return null;
    }
    public CustomerDataDto getCustomerData(Long customernumber) {
        CustomerDataDto customerDto = new CustomerDataDto();

        Optional<Customer> customerOptional = customerRepository.findById(customernumber);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            customerDto.setCustomer(customerDtoTransformer.getCustomerDtoFromCustomer(customer));
            customerDto
                    .setReservations(reservationRepository.findAllByCustomer(customer));
            customerDto.setPayments(paymentRepository.findAllByCustomer(customer));

            return customerDto;
        }
        return null;
    }
}
