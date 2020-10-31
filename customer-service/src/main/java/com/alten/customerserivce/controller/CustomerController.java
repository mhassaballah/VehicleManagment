package com.alten.customerserivce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alten.customerserivce.model.Customer;
import com.alten.customerserivce.service.CustomerService;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@GetMapping(path = "/customers/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		log.info("Get All Customers");
		return ResponseEntity.ok(customerService.findAll());
	}
	@GetMapping(path = "/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
		log.info("Get  Customer by id {}",id);
		try {
			return ResponseEntity.ok(customerService.getCustomerById(id));
		}catch (NotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND,e.getMessage(), e);
		}
	}
}
