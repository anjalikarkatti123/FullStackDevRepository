package com.gentech.customer.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	@Override
	public List<Customer> getAllCustomersByCustomerName(String name) {
		return custRepository.findByCustomerName(name);
	}

	@Override
	public List<Customer> getAllCustomersByLocation(String name) {
		return custRepository.findByLocation(name);
	}

	@Override
	public List<Customer> getAllCustomersByNameAndLocation(String name, String loc) {
		return custRepository.findByCustomerNameAndLocation(name, loc);
	}

	@Override
	public List<Customer> getAllCustomersByPartialCustomerName(String name) {
		return custRepository.findByCustomerNameContaining(name);
	}

	@Override
	public List<Customer> getCustomers(int pageNumber, int pageSize) {
		
		Pageable pages = PageRequest.of(pageNumber, pageSize);
		return custRepository.findAll(pages).getContent();
	}

	@Override
	public List<Customer> getCustomers(int pageNumber, int pageSize, String columnName) {
		Sort sort=Sort.by(Direction.ASC, columnName);
		Pageable pages = PageRequest.of(pageNumber, pageSize, sort);
		return custRepository.findAll(pages).getContent();
	}

	@Override
	public List<Customer> getAllCustomersByNameOrLocation(String name, String loc) {
		return custRepository.getAllCustomersByNameAndLocation(name, loc);
	}

	@Override
	public Integer deleteBySpecificCustomerName(String name) {
		return custRepository.getDeleteByCustomerName(name);
	}

	@Override
	public Integer updateEmailForSpecificCustomer(String email, String customerName) {
		return custRepository.updateEmailIdByCustomerName(email,customerName);
	}

	@Override
	public Integer updateCustomerNameAndLocationById(String name, String location, Long id) {
		return custRepository.updateCustomerNameAndLocationById(name, location, id);
	}

	
	
	
}
