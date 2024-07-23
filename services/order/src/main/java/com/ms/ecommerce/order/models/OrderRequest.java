package com.ms.ecommerce.order.models;

import com.ms.ecommerce.order.entities.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Costumer should be present")
        @NotEmpty(message = "Costumer should be present")
        @NotBlank(message = "Costumer should be present")
        String customerId,
        @NotEmpty(message = "You should provide at least one product")
        List<PurchaseRequest> products
) {
}
