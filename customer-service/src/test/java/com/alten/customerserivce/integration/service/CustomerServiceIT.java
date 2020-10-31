package com.alten.customerserivce.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.service.CustomerService;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIT{

	@Autowired
	private CustomerService customerService; 
	@Test
    public void testFindAllCustomers() {
           List<Customer> allCustomers = customerService.findAll();
           assertThat(allCustomers).isNotNull().isNotEmpty();
           assertThat(allCustomers.get(0).getCustomerName())
 	      .isEqualTo("Kalles Grustransporter");
    }
	@Test
    public void testGetCustomerById() {
           Customer customer;
		try {
			customer = customerService.getCustomerById(1l);
			  assertThat(customer).isNotNull();
	           assertThat(customer.getCustomerName())
	 	      .isEqualTo("Kalles Grustransporter");
		} catch (NotFoundException e) {
			  assertThat(e.getMessage())
	 	      .isEqualTo("No customer Found with id "+ 1l);
		}
         
    }
}
