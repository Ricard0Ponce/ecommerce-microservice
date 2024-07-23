package com.ms.ecommerce.product.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
//Nota: Al ser un record no se pueden usar las anotaciones de Lombok aqui

public record ProductRequest(
        Integer id,
        @NotNull(message = "Product name is requiered")
        String name,
        @NotNull(message = "Product description is requiered")
        String description,
        @Positive(message = "Product quantity must be greater than 0")
        double availiableQuantity,
        @Positive(message = "Product price must be greater than 0")
        BigDecimal price,
        @NotNull(message = "Product category is requiered")
        Integer categoryId
) {
}
