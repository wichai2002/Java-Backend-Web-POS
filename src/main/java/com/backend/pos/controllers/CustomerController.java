package com.backend.pos.controllers;


import com.backend.pos.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.pos.entity.Customer;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try{
            List<Customer> customers = customerService.findAll();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        try{
            Optional<Customer> customer = customerService.findById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try{
            Customer savedCustomer = customerService.save(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        }catch(Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        try {
            Optional<Customer> customerOptional = customerService.findById(id);
            if (customerOptional.isPresent()) {
                Customer customerToUpdate = customerOptional.get();
                BeanUtils.copyProperties(
                        customer,
                        customerToUpdate,
                        "id",
                        "memberedAt",
                        "lastOrder"
                );
                customerService.save(customerToUpdate);
                return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int id) {
        try{
            Optional<Customer> customerOptional = customerService.findById(id);
            if (customerOptional.isPresent()) {
                customerService.deleteById(id);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
