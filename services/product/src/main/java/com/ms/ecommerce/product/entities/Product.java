package com.ms.ecommerce.product.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private double availiableQuantity;
    private BigDecimal price;
    @ManyToOne // Recordatorio: Si el objeto es lista es OneToMany
    // Si el objeto es un solo objeto es ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
