package com.ms.ecommerce.order.services;

import com.ms.ecommerce.order.models.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Integer createdOrder(OrderRequest orderRequest);
}
