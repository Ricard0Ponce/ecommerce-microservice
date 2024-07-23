package com.ms.ecommerce.order.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne // Indicamos la relacion de muchos a uno
    @JoinColumn(name = "order_id") // JoinColumn permite indicar el nombre de la columna que se va a relacionar
    private Order order;
    private Integer productId; // Indica el ID del producto
    private double quantity; // Indica la cantidad de productos
}
