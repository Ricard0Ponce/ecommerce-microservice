package com.ms.ecommerce.order.models;

public record CustomerResponse(
        String id,
        String firsname,
        String lastname,
        String email
) {
}
