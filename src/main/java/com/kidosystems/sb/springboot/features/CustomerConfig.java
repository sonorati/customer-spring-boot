package com.kidosystems.sb.springboot.features;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    public CustomerService customerService(final CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }

}
