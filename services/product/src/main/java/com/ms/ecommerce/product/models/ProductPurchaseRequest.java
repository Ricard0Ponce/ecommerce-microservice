package com.ms.ecommerce.product.models;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
}
