package com.ms.ecommerce.product.repositories;

import com.ms.ecommerce.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
