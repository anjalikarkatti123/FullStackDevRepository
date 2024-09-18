package com.gentech.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gentech.customer.entity.Customer;
import com.gentech.customer.service.CustomerService;
@RestController
@RequestMapping("/v1/api")
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	// http://localhost:9092/v1/api/customer
	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
	{
		return new ResponseEntity<Customer>(custService.createCustomer(customer), HttpStatusCode.valueOf(201));
	}
	
	// http://localhost:9092/v1/api/customers
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return new ResponseEntity<List<Customer>>(custService.getCustomers(), HttpStatusCode.valueOf(200));
	}
	
	// http://localhost:9092/v1/api/customer/{id}
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getSpecificCustomer(@PathVariable("id") Long customerId)
	{
		return new ResponseEntity<Customer>(custService.getCustomer(customerId), HttpStatusCode.valueOf(200));
	}
	
	// http://localhost:9092/v1/api/customer/{id}
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> modifySpecificCustomer(@PathVariable Long id, 
			@RequestBody Customer customer)
	{
		return new ResponseEntity<Customer>(custService.updateCustomer(customer, id), HttpStatusCode.valueOf(200));
	}
	
	// http://localhost:9092/v1/api/customer/{id}
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteSpecificCustomer(@PathVariable Long id)
	{
		custService.deleteCustomer(id);
		return new ResponseEntity<String>("The Customer id "+id+" has deleted successfully", HttpStatusCode.valueOf(200));
	}
	
}
