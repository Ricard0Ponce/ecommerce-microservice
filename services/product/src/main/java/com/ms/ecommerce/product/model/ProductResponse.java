package com.ms.ecommerce.product.model;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availiableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
