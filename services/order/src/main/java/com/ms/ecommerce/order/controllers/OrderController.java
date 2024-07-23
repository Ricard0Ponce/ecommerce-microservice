package com.ms.ecommerce.order.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ms.ecommerce.order.services.OrderService;
import com.ms.ecommerce.order.models.OrderRequest;

@RestController
@RequestMapping("/api/v1/prders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;


    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest) {

        return ResponseEntity.ok(orderService.createdOrder(orderRequest));
    }
}
