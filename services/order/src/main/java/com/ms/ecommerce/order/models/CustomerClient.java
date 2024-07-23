package com.ms.ecommerce.order.models;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url="${application.config.customer-url}"
)
//Esta interfaz define el cliente para el microservicio de clientes, usa la anotación @FeignClient para definir el nombre del microservicio y la URL de este.
public interface CustomerClient {

    // Gracias a la anotación @GetMapping, Feign sabe que debe hacer una petición GET al microservicio de clientes, con la URL /{customer-id} y el parámetro customerId.
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerByid(@PathVariable("customer-id") String customerId);
}
