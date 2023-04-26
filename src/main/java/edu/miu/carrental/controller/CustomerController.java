package edu.miu.carrental.controller;

import edu.miu.carrental.domain.dto.CustomerDataDto;
import edu.miu.carrental.domain.dto.CustomerDto;
import edu.miu.carrental.domain.dto.CustomersDto;
import edu.miu.carrental.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author bazz
 * Apr 24 2023
 * 18:06
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping
    public ResponseEntity<CustomersDto> getAllCustomers() {
        log.info("GETTING ALL CUSTOMER ENDPOINT CALLED====");
        CustomersDto customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<CustomersDto> searchCustomer(@RequestParam("searchBy") String searchBy, @RequestParam("value") String value){

        log.info("SEARCH CUSTOMER ENDPOINT CALLED====");
        return new ResponseEntity<CustomersDto>(customerService.searchCustomer(searchBy,value),HttpStatus.OK);
    }
    @GetMapping("{customernumber}/get")
    public ResponseEntity<CustomerDataDto> getCustomerData(@PathVariable("customernumber") Long customernumber){

        log.info("GET CUSTOMERDATA ENDPOINT CALLED====");
        return new ResponseEntity<CustomerDataDto>(customerService.getCustomerData(customernumber),HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customer) {
        log.info("CREATE CUSTOMER ENDPOINT CALLED====");

        CustomerDto createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("update/{customernumber}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customernumber, @RequestBody CustomerDto customer) {
        log.info("UPDATE CUSTOMER ENDPOINT CALLED====");

        CustomerDto updatedCustomer = customerService.updateCustomer(customernumber, customer);
        if (updatedCustomer == null) {
            log.warn("UPDATE CUSTOMER ENDPOINT CALLED FAILED====");

            return new ResponseEntity<String>("Invalid Customer Number, please check and try again", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{customernumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customernumber) {
        log.info("DELETE CUSTOMER ENDPOINT CALLED ====");

        boolean deleted = customerService.deleteCustomer(customernumber);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.warn("DELETE CUSTOMER ENDPOINT CALLED FAILED====");

        return new ResponseEntity<>("Invalid Customer Number, please check and try again", HttpStatus.NOT_FOUND);
    }
}
