package com.ms.ecommerce.order.repositories;

import com.ms.ecommerce.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
