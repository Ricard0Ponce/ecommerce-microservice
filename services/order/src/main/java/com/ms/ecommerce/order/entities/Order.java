package com.ms.ecommerce.order.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static jakarta.persistence.EnumType.STRING;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) // Nos permite auditar la entidad
@Table(name="customer_order") // Le damos nombre a la tabla
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(STRING) // Esta anotacion es para indicar que el atributo es un Enum
    private PaymentMethod paymentMethod;
    // El siguiente atributo trae información del cliente
    private String customerId;
    @OneToMany(mappedBy = "order") // Esta anotacion nos permite indicar que la relacion es de uno a muchos
    private List<OrderLine> orderLines;
    @CreatedDate // Esta etiqueta nos permite auditar la fecha en la que se creo el registro
    @Column(updatable = false, nullable = false) // Esta etiqueta nos permite indicar que el campo no puede ser nulo y que no se puede actualizar
    private LocalDateTime createdAt;
    @LastModifiedDate // Esta etiqueta nos permite auditar la fecha en la que se actualizo el registro
    @Column(insertable = false) // Esta etiqueta nos permite indicar que el campo no puede ser nulo y que no se puede insertar
    private LocalDateTime lastModifiedDate;

}
