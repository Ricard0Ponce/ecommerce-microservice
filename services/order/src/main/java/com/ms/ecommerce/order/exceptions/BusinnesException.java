package com.ms.ecommerce.order.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // Esta anotación genera los métodos equals y hashcode
@Data // Genera los métodos getter y setter
public class BusinnesException extends RuntimeException{

    private final String msg;

}
