package com.ms.ecommerce.order.services;

import com.ms.ecommerce.order.models.*;
import com.ms.ecommerce.order.exceptions.BusinnesException;
import com.ms.ecommerce.order.repositories.OrderRepository;
import com.ms.ecommerce.order.mapper.OrderMapper;

public class OrderServiceImpl implements OrderService{
    private OrderRepository repository; // Repositorio de ordenes
    private CustomerClient customerClient; // Cliente para el microservicio de clientes
    private ProductClient productClient; // Cliente para el microservicio de productos
    private OrderMapper mapper; // Mapper para mapear la orden
    private OrderLineServiceImpl orderLineService; // Servicio para las lineas de orden

    @Override
    public Integer createdOrder(OrderRequest orderRequest) {
        // Revisar si el cliente existe: -- > Usamos OpenFeign
        var customer  = this.customerClient.findCustomerByid(orderRequest.customerId())
                .orElseThrow(() -> new BusinnesException("Cannot create order :: No customer found with id " + orderRequest.customerId()));

        // Revisar si los productos existen: --> Usamos el Microservicio de Productos con RestTemplate
        this.productClient.purchaseProducts(orderRequest.products()); // Revisar si los productos existen

        //Almacenar la orden en la base de datos
        var order = this.repository.save(mapper.toOrder(orderRequest)); // Guardar la orden en la base de datos

        // Persist order lines in the database
        // El siguiente bloque de código nos permite guardar las lineas de orden en la base de datos
        for(PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // Iniciar el proceso de pago
        // Enviar la confirmación de la orden al microservicio de notificaciones (kafka)
        return 0;
    }
}
