package com.alten.customerserivce.unit.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alten.customerserivce.controller.CustomerController;
import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.service.CustomerService;
import com.alten.customerserivce.util.CustomerUtil;



@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private CustomerService customerService;
	
	@Test
	public void shouldGetAllCustomers()
	  throws Exception {
	     
	   
	    List<Customer> allCustomers =CustomerUtil.prepareCustomers();
	 
	    given(customerService.findAll()).willReturn(allCustomers);
	 
	    mvc.perform(get("/customers/"))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(2))) 
	       .andExpect(jsonPath("$[0].customerId").value(allCustomers.get(0).getCustomerId()))
	      .andExpect(jsonPath("$[0].customerName", is(allCustomers.get(0).getCustomerName())))
	      .andExpect(jsonPath("$[0].customerAddress", is(allCustomers.get(0).getCustomerAddress())))
	      .andExpect(jsonPath("$[1].customerId").value(allCustomers.get(1).getCustomerId()))
	      .andExpect(jsonPath("$[1].customerName", is(allCustomers.get(1).getCustomerName())))
	      .andExpect(jsonPath("$[1].customerAddress", is(allCustomers.get(1).getCustomerAddress())));
	
	   
	}
	@Test
	public void testGetCustomerById()
	  throws Exception {
	     
	   
	    Customer customer =CustomerUtil.prepareCustomers().get(0);
	 
	    given(customerService.getCustomerById(1)).willReturn(customer);
	 
	    mvc.perform(get("/"+1))
	      .andExpect(status().isOk())
	       .andExpect(jsonPath("$.customerId").value(customer.getCustomerId()))
	      .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
	      .andExpect(jsonPath("$.customerAddress", is(customer.getCustomerAddress())));	
	   
	}
	

}
