package com.alten.customerserivce.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.repository.CustomerRepository;
import com.alten.customerserivce.service.CustomerService;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
public class CustomerSerivceTest {

	 @TestConfiguration
	    static class CustomerServiceImplTestContextConfiguration {
	  
	        @Bean
	        public CustomerService customerService() {
	            return new CustomerService();
	        }
	    }
	@Autowired
	private CustomerService customerService;
	@MockBean
	private CustomerRepository customerRepository;
	
	@Before
	public void setUp() {
	    Mockito.when(customerRepository.findAll())
	      .thenReturn(prepareCustomers());
	    Mockito.when(customerRepository.findById(1l))
	      .thenReturn(Optional.of(prepareCustomers().get(0)));
	}
	@Test
	public void validatecustomers() {
	    List<Customer> found = customerService.findAll();
	  
	    assertThat(found.get(0).getCustomerId())
	      .isEqualTo(1L);
	    assertThat(found.get(1).getCustomerId())
	      .isEqualTo(2L);
	    assertThat(found.get(0).getCustomerAddress())
	      .isEqualTo("Address 1");
	    assertThat(found.get(1).getCustomerAddress())
	      .isEqualTo("Address 2");
	    assertThat(found.get(0).getCustomerName())
	      .isEqualTo("Customer1");
	    assertThat(found.get(1).getCustomerName())
	      .isEqualTo("Customer2");
	    assertThat(found.size())
	      .isEqualTo(2);
	 }
	@Test
	public void validateCustomerById() {
	    Customer found;
		try {
			found = customerService.getCustomerById(1l);
			 assertThat(found.getCustomerId())
		      .isEqualTo(1L);
		    assertThat(found.getCustomerAddress())
		      .isEqualTo("Address 1");
		    assertThat(found.getCustomerName())
		      .isEqualTo("Customer1");
		} catch (NotFoundException e) {
			assertThat(e.getMessage())
		      .isEqualTo("No customer Found with id "+ 1l);
		}

	   

	 }
	private List<Customer> prepareCustomers(){
	    Customer customer1 = new Customer();
	    customer1.setCustomerId(1L);
	    customer1.setCustomerName("Customer1");
	    customer1.setCustomerAddress("Address 1");
	    
	    Customer customer2 = new Customer();
	    customer2.setCustomerId(2L);
	    customer2.setCustomerName("Customer2");
	    customer2.setCustomerAddress("Address 2");
	    List<Customer> customers=new ArrayList<>();
	    customers.add(customer1);
	    customers.add(customer2);
	   return customers;
}
}
