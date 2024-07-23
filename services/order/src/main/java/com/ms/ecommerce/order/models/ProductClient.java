package com.ms.ecommerce.order.models;

import com.ms.ecommerce.order.exceptions.BusinnesException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.core.ParameterizedTypeReference;
// Esta clase se usa para comunicarse con el microservicio de productos con RestTemplate
// En este caso agregamos la anotación @Service para que Spring la pueda inyectar
@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    // Este método se usa para enviar una solicitud al microservicio de productos para comprar productos
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders(); // Creamos un objeto HttpHeaders
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE); // Agregamos el tipo de contenido

        // Creamos un objeto HttpEntity con el cuerpo de la solicitud y las cabeceras
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        // Creamos un objeto ParameterizedTypeReference para obtener una lista de objetos PurchaseResponse
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        // Enviamos una solicitud POST al microservicio de productos para comprar productos
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestEntity,
                responseType
        );
 // new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        if (responseEntity.getStatusCode().isError()) {
            // Si la respuesta es un error, lanzamos una excepción
            throw new BusinnesException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        // Devolvemos el cuerpo de la respuesta, que es de tipo List<PurchaseResponse>
        return  responseEntity.getBody();
    }

}
