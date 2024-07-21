package com.ms.ecommerce.customer.services;

import com.ms.ecommerce.customer.models.CustomerRequest;
import com.ms.ecommerce.customer.models.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    String createCustomer(CustomerRequest customerRequest);
    ResponseEntity<?> updateCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> findAllCustomers();
    Boolean exists(String customerId);
    CustomerResponse getCustomer(String customerId);
    void deleteCustomer(String customerId);
}
