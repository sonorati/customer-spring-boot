package com.kidosystems.sb.springboot.features;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/customers",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResource> create(@RequestBody final CustomerResource customerResource) {
         final Customer customer = customerService.create(customerResource.getFirstName(),
                 customerResource.getSurname());

        final CustomerResource payload = new CustomerResource(customer.getGuid(),
                customer.getFirstName(), customer.getSurname());
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }

    @GetMapping(path = "/customers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CustomerResource>> getAll() {
        final List<Customer> customers = customerService.getAll();

        final List<CustomerResource> customerResources = customers.stream()
                .map(customer -> new CustomerResource(customer.getGuid(), customer.getFirstName(),
                        customer.getSurname()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerResources, HttpStatus.OK);
    }
}
