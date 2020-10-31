package com.alten.customerserivce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

    @HystrixCommand(fallbackMethod = "getDefaultCustomers",
    		commandProperties = {
    			       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),
    			       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
    			    })
	public List<Customer> findAll() {
		log.info("Find All Customers ");
		return customerRepository.findAll();
	}
    @HystrixCommand(fallbackMethod = "getDefaultCustomer",
    		commandProperties = {
    			       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000"),
    			       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
    			    })
    public Customer getCustomerById(long id) throws NotFoundException {
		log.info("Find Customer by id");
		Optional<Customer> optionalCustomer=customerRepository.findById(id);
		if(optionalCustomer.isPresent())
			return optionalCustomer.get();
		else 
		    throw new NotFoundException("No customer Found with id "+ id);
	}
    public List<Customer> getDefaultCustomers() {
		log.info("prepare default  Customers ");
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
    public Customer getDefaultCustomer(long id) {
		log.info("prepare default  Customer ");
		return getDefaultCustomers().get(0);
	}
}
