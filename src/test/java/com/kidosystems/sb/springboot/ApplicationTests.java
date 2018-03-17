package com.kidosystems.sb.springboot;

import com.google.common.collect.Lists;
import com.kidosystems.sb.springboot.features.Customer;
import com.kidosystems.sb.springboot.features.CustomerRepository;
import com.kidosystems.sb.springboot.features.CustomerResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.test.properties")
public class ApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldCreateCustomer() {
		final CustomerResource customerResource = new CustomerResource(null, "test1", "test2");
		final ResponseEntity<CustomerResource> result = restTemplate.postForEntity("/customers",
				customerResource, CustomerResource.class);

		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getBody().getFirstName()).isEqualTo(customerResource.getFirstName());
		assertThat(result.getBody().getSurname()).isEqualTo(customerResource.getSurname());
		assertThat(result.getBody().getGuid()).isNotNull();
	}

	@Test
	public void shouldGetCustomers() {
		//Given
		final Customer customer1 = new Customer(UUID.randomUUID(), "test1", "test1");
		final Customer customer2 = new Customer(UUID.randomUUID(), "test2", "test2");
		final List<Customer> customers = Lists.newArrayList(customer1, customer2);
		customerRepository.save(customers);

		//When
		final ResponseEntity<List> result = restTemplate.getForEntity("/customers", List.class);

		//Then
		assertThat(result.getBody()).hasSameSizeAs(customers);
	}

}
