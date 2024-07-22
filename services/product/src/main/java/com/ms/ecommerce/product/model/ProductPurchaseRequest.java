package com.ms.ecommerce.product.model;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
}
