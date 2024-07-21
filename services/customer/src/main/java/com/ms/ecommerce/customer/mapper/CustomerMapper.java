package com.ms.ecommerce.customer.mapper;

import com.ms.ecommerce.customer.entities.Customer;
import com.ms.ecommerce.customer.models.CustomerRequest;
import com.ms.ecommerce.customer.models.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customer) {
        if(customer == null) {
            return null;
        }
        return Customer.builder()
                .id(customer.id())
                .firstname(customer.firstname())
                .lastname(customer.lastname())
                .email(customer.email())
                .address(customer.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );

    }
}
