package com.ms.ecommerce.order.services;
import com.ms.ecommerce.order.mapper.OrderLineMapper;
import com.ms.ecommerce.order.models.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ms.ecommerce.order.repositories.OrderLineRepository;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
