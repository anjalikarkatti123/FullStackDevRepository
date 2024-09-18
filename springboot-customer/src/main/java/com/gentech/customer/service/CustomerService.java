package com.gentech.customer.service;

import java.util.List;

import com.gentech.customer.entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);
	
	List<Customer> getCustomers();
	
	Customer getCustomer(Long id);
	
	Customer updateCustomer(Customer customer, Long id);
	
	void deleteCustomer(Long id);
}
