package com.ms.ecommerce.product.services;

import com.ms.ecommerce.product.models.ProductPurchaseRequest;
import com.ms.ecommerce.product.models.ProductPurchaseResponse;
import com.ms.ecommerce.product.models.ProductRequest;
import com.ms.ecommerce.product.models.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
