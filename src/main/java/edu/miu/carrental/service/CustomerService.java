package edu.miu.carrental.service;

import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.domain.dto.CustomerDtoTransformer;
import edu.miu.carrental.domain.dto.CustomersDto;
import edu.miu.carrental.domain.entity.Customer;
import edu.miu.carrental.respository.CustomerRepository;
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

    public CustomersDto getAllCustomers(){
        return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAll());
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto dto){
        Customer customer = new Customer();
        customer.setCustomerNumber(dto.getCustomerNumber());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.save(customer));
    }

    public CustomerDto updateCustomer(Long customerNumber, CustomerDto dto){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerNumber);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setEmail(dto.getEmail());
            customer.setName(dto.getName());
           return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.save(customer));
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
                return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByCustomerNumber(value));
            case "name":
                return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByNameIgnoreCase(value));
            case "email":
                return CustomerDtoTransformer.getCustomersDtoFromCustomers(customerRepository.findAllByEmailIgnoreCase(value));
        }
        return null;
    }
}
