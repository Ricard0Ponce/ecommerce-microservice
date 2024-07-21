package com.ms.ecommerce.customer.models;


import com.ms.ecommerce.customer.entities.Address;
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
