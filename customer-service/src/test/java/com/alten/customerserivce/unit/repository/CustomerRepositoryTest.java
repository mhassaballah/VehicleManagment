package com.alten.customerserivce.unit.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.repository.CustomerRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void whenFindllCustomers_thenReturnCustomers() {
	    // given
		 List<Customer> customers=prepareCustomers();
		 for (Iterator<Customer> it = customers.iterator(); it.hasNext();) {
			 Customer customer = it.next();
			 entityManager.persist(customer);
			 entityManager.flush();
			 entityManager.clear();
	        }
	 
	    // when
		 List<Customer> found = customerRepository.findAll();
	 
	    // then
	    assertThat(found.get(0).getCustomerId())
	      .isEqualTo(customers.get(0).getCustomerId());
	    assertThat(found.get(1).getCustomerId())
	      .isEqualTo(customers.get(1).getCustomerId());
	    assertThat(found.get(0).getCustomerAddress())
	      .isEqualTo(customers.get(0).getCustomerAddress());
	    assertThat(found.get(1).getCustomerAddress())
	      .isEqualTo(customers.get(1).getCustomerAddress());
	    assertThat(found.get(0).getCustomerName())
	      .isEqualTo(customers.get(0).getCustomerName());
	    assertThat(found.get(1).getCustomerName())
	      .isEqualTo(customers.get(1).getCustomerName());
	    assertThat(found.size())
	      .isEqualTo(customers.size());
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
