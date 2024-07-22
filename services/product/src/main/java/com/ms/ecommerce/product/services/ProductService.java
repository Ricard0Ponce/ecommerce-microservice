package com.ms.ecommerce.product.services;

import com.ms.ecommerce.product.model.ProductPurchaseRequest;
import com.ms.ecommerce.product.model.ProductPurchaseResponse;
import com.ms.ecommerce.product.model.ProductRequest;
import com.ms.ecommerce.product.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
