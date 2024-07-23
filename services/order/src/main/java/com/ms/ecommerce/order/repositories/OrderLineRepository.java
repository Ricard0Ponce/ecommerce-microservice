package com.ms.ecommerce.order.repositories;

import com.ms.ecommerce.order.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
