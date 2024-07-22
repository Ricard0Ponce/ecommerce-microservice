package com.ms.ecommerce.customer.controllers;

import com.ms.ecommerce.customer.models.CustomerRequest;
import com.ms.ecommerce.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ms.ecommerce.customer.models.CustomerResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest ){
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Customer updated");
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    // Este endpoint solo revisa si esta el cliente o no
    @GetMapping("exists/{customer-id}")
    public ResponseEntity<Boolean> exist(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.exists(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
