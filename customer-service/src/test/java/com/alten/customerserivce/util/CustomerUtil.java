package com.alten.customerserivce.util;

import java.util.ArrayList;
import java.util.List;

import com.alten.customerserivce.model.Customer;

public class CustomerUtil {

	public static List<Customer> prepareCustomers(){
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
