package com.example.demo2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo2.entity.Customer;

import java.util.List;

@RestController
@EnableMethodSecurity
public class CustomerController {

    // List of customers as a sample data source
    final private List<Customer> customers = List.of(
            Customer.builder().id("001").name("Nguyễn Hữu Trung").email("trungnhspkt@gmail.com").build(),
            Customer.builder().id("002").name("Hữu Trung").email("trunghuu@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, Guest");
    }

    // Endpoint to return a list of customers, accessible only by users with ROLE_ADMIN
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(this.customers);
    }

    // Endpoint to return a customer by ID, accessible only by users with ROLE_USER
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
        // Filter the customer by ID and return the first match
        List<Customer> filteredCustomers = this.customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .toList();
        
        if (filteredCustomers.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if customer not found
        }

        return ResponseEntity.ok(filteredCustomers.get(0));
    }
}
