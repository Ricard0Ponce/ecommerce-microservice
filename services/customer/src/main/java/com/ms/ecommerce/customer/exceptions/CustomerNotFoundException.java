package com.ms.ecommerce.customer.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Anotacion de Lombok para generar equals y hashcode
@Data
public class CustomerNotFoundException extends RuntimeException {

    private final String msg;
}
