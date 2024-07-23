package com.ms.ecommerce.order.services;

import com.ms.ecommerce.order.models.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrderLineService {

    public Integer saveOrderLine(OrderLineRequest orderLineRequest);
}
