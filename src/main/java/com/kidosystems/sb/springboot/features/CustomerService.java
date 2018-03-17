package com.kidosystems.sb.springboot.features;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(final String firstName,
                           final String surname) {
        final Customer customer = new Customer(UUID.randomUUID(), firstName, surname);
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return Lists.newArrayList(customerRepository.findAll());
    }
}
