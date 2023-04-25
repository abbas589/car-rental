package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.domain.dto.CustomersDto;
import edu.miu.carrental.domain.entity.Customer;
import edu.miu.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bazz
 * Apr 24 2023
 * 18:06
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomersDto> getAllCustomers() {
        CustomersDto customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<CustomersDto> searchCustomer(@RequestParam("searchBy") String searchBy, @RequestParam("value") String value){
        return new ResponseEntity<CustomersDto>(customerService.searchCustomer(searchBy,value),HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
        CustomerDto createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("update/{customernumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customernumber, @RequestBody CustomerDto customer) {
        CustomerDto updatedCustomer = customerService.updateCustomer(customernumber, customer);
        if (updatedCustomer == null) {
            return new ResponseEntity<String>("Invalid Customer Number, please check and try again", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customernumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customernumber) {
        boolean deleted = customerService.deleteCustomer(customernumber);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Invalid Customer Number, please check and try again", HttpStatus.NOT_FOUND);
    }
}
