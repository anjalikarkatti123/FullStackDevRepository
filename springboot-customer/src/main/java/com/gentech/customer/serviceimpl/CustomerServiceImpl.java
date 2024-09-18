package com.gentech.customer.serviceimpl;

import java.util.List;

import org.hibernate.query.sqm.tree.domain.AbstractSqmSpecificPluralPartPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gentech.customer.entity.Customer;
import com.gentech.customer.exception.ResourceNotFoundException;
import com.gentech.customer.repository.CustomerRepository;
import com.gentech.customer.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository custRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		return custRepository.save(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		return custRepository.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {
		return custRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Customer", "id", id));
	}

	@Override
	public Customer updateCustomer(Customer customer, Long id) {
		 Customer existingCustomer=custRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Customer", "id", id));
		
		 existingCustomer.setCustomerName(customer.getCustomerName());
		 existingCustomer.setEmailId(customer.getEmailId());
		 existingCustomer.setLocation(customer.getLocation());
		 
		return custRepository.save(existingCustomer);
	}

	@Override
	public void deleteCustomer(Long id) {
		 custRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Customer", "id", id));
		 
		 custRepository.deleteById(id);
		
	}

	
}
