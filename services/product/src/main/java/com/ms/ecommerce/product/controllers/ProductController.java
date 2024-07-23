package com.ms.ecommerce.product.controllers;

import com.ms.ecommerce.product.models.ProductRequest;
import com.ms.ecommerce.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ms.ecommerce.product.models.ProductPurchaseResponse;
import com.ms.ecommerce.product.models.ProductPurchaseRequest;
import com.ms.ecommerce.product.models.ProductResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> requestList
    ){
        return ResponseEntity.ok(productService.purchaseProducts(requestList));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
        @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }


}
