package com.ms.ecommerce.customer.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class Address {

    @NotNull(message = "Street is required")
    private String street;
    @NotNull(message = "House number is required")
    private String houseNumber;
    @NotNull(message = "Zip Code is required")
    private String zipCode;
}
