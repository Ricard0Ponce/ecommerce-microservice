package com.ms.ecommerce.customer.services;

import com.ctc.wstx.util.StringUtil;
import com.ms.ecommerce.customer.entities.Customer;
import com.ms.ecommerce.customer.mapper.CustomerMapper;
import com.ms.ecommerce.customer.models.CustomerRequest;
import com.ms.ecommerce.customer.models.CustomerResponse;
import com.ms.ecommerce.customer.repositories.CustomerRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ms.ecommerce.customer.exceptions.CustomerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository; // Aqui podria ir final
    @Autowired
    private CustomerMapper mapper;


    @Override
    @Transactional
    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(mapper.toCustomer(customerRequest));
        return customer.getId();
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateCustomer(CustomerRequest customerRequest) {
        var customer= customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Cannot update a customer :: " +
                        "No customer found with id %s", customerRequest.id())));
        mergerCustomer(customer, customerRequest);
        customerRepository.save(customer);
        return ResponseEntity.ok().build();
    }


    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }


    // Devuelve un booleano si existe o no el cliente
    @Transactional(readOnly = true)
    @Override
    public Boolean exists(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with id %s", customerId)));
    }

    @Override
    public void deleteCustomer(String customerId) {
        // Primero se valida si el customer existe
        if(!customerRepository.existsById(customerId)) {
            // Si no existe se lanza una excepcion
             throw new CustomerNotFoundException(String.format("No customer found with id %s", customerId));
        }
        //Si existe se elimina
        customerRepository.deleteById(customerId);
    }


    // Con esto se valida que los campos no esten vacios para poder actualizar
    private void mergerCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname())) {
            customer.setFirstname(customerRequest.firstname());
        }
        if(StringUtils.isNotBlank(customerRequest.lastname())) {
            customer.setFirstname(customerRequest.lastname());
        }
        if(StringUtils.isNotBlank(customerRequest.email())) {
            customer.setFirstname(customerRequest.email());
        }
        if(customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }

    }

}
