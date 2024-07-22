package com.ms.ecommerce.product.services;

import com.ms.ecommerce.product.mapper.ProductMapper;
import com.ms.ecommerce.product.model.ProductPurchaseRequest;
import com.ms.ecommerce.product.model.ProductPurchaseResponse;
import com.ms.ecommerce.product.model.ProductRequest;
import com.ms.ecommerce.product.model.ProductResponse;
import com.ms.ecommerce.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.ms.ecommerce.product.exceptions.ProductPurchaseException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    @Override
    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        // Comenzamos por obtener los ids de los productos que se quieren comprar
        var productIds = requestList
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        // Luego se obtienen los productos de la base de datos
        var storedProucts = productRepository.findAllByIdInOrderById(productIds);
        // Posteriormente se revisa si todos los productos existen
        if(productIds.size()!= storedProucts.size()) { // Si los tamaños son diferentes, entonces hay productos que no existen
            throw new ProductPurchaseException("Some products do not exist");
        }
        // Si todos los productos existen, se procede al siguiente paso
        var storeRequest = requestList
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i =0; i < storedProucts.size(); i++) {
            var product = storedProucts.get(i); // Se obtiene el producto almacenado
            var productRequest = storeRequest.get(i); // Se obtiene el producto de la solicitud
            // Ahora revisamos si hay suficiente stock del producto solicitado
            if(product.getAvailiableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Not enough stock for product with id: " + product.getId());
            }
            // Si hay suficiente stock, se procede a realizar la compra
            var newAvailableQuantity = product.getAvailiableQuantity() - productRequest.quantity(); // Se calcula la nueva cantidad disponible tras la compra
            product.setAvailiableQuantity(newAvailableQuantity); // Se actualiza la cantidad disponible
            productRepository.save(product); // Se guarda el producto actualizado
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity())); // Se agrega el producto a la lista de productos comprados
        }
        return purchaseProducts;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(Long.valueOf(productId))
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
