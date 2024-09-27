package com.gentech.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
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

	// http://localhost:9092/v1/api/customer/filterbyname?name=Adams
	@GetMapping("/customer/filterbyname")
	public ResponseEntity<List<Customer>> getAllCustomersByName(@RequestParam String name)
	{
		return new ResponseEntity<List<Customer>>(custService.getAllCustomersByCustomerName(name), HttpStatusCode.valueOf(200));
	}

	// http://localhost:9092/v1/api/customer/filterbyloc?loc=Paris
	@GetMapping("/customer/filterbyloc")
	public ResponseEntity<List<Customer>> getAllCustomersByLocation(@RequestParam String loc)
	{
		return new ResponseEntity<List<Customer>>(custService.getAllCustomersByLocation(loc), HttpStatusCode.valueOf(200));
	}

	// http://localhost:9092/v1/api/customer/filterbynameandloc?name=Adams&loc=London
	@GetMapping("/customer/filterbynameandloc")
	public ResponseEntity<List<Customer>> getAllCustomersByNameAndLocation(String name,String loc)
	{
		return new ResponseEntity<List<Customer>>(custService.getAllCustomersByNameAndLocation(name, loc), HttpStatus.OK);
	}

	// http://localhost:9092/v1/api/customer/filterbypartialname?name=RD
	@GetMapping("/customer/filterbypartialname")
	public ResponseEntity<List<Customer>> getAllCustomersByPartialCustomerName(@RequestParam String name)
	{
		return new ResponseEntity<List<Customer>>(custService.getAllCustomersByPartialCustomerName(name), HttpStatus.OK);
	}

	// http://localhost:9092/v1/api/customer/pagination?pageSize=5
	@GetMapping("/customer/pagination")
	public ResponseEntity<List<Customer>> getAllCustomersByPagination(@RequestParam int pageNumber, 
			@RequestParam int pageSize)
	{
		return new ResponseEntity<List<Customer>>(custService.getCustomers(pageNumber,pageSize), HttpStatus.OK);
	}

	// http://localhost:9092/v1/api/customer/pagination?pageNumber=1&pageSize=5&columnName=id
	@GetMapping("/customer/pageandsort")
	public ResponseEntity<List<Customer>> getAllCustomersByPaginationAndSort(@RequestParam int pageNumber, 
			@RequestParam int pageSize,@RequestParam String columnName)
	{

		return new ResponseEntity<List<Customer>>(custService.getCustomers(pageNumber, pageSize, columnName), HttpStatus.OK);
	}

	// http://localhost:9092/v1/api/customer/query?name=Adams&loc=Dallas
	@GetMapping("/customer/query")
	public ResponseEntity<List<Customer>> getAllCustomersByNameOrLocation(@RequestParam String name,@RequestParam String loc)
	{
		return new ResponseEntity<List<Customer>>(custService.getAllCustomersByNameOrLocation(name, loc), HttpStatus.OK);
	}
	
	
	// http://localhost:9092/v1/api/customer?name=Miller
	@DeleteMapping("/customer")
	public ResponseEntity<String> deleteBasedOnCustomerName(@RequestParam String name)
	{
		Integer count=custService.deleteBySpecificCustomerName(name);
		return new ResponseEntity<String>(count+" Customer records have deleted successfully", HttpStatus.OK);
	}
	
	// http://localhost:9092/v1/api/customer?email=xx@xx.xom&name=Miller
	@PutMapping("customer")
	public ResponseEntity<String> putMethodName(@RequestParam String email,@RequestParam String customername) {
		
		Integer count=custService.updateEmailForSpecificCustomer(email,customername);
		return new ResponseEntity<String>(count+" customer record has updated successfully", HttpStatus.OK);
				
	}
	
	// http://localhost:9091/v1/api/customer/multiple?name=Ganesh&loc=Mangalore&id=21
	@PutMapping("/customer/multiple")
	public ResponseEntity<String> updateNameAndLocById(@RequestParam String name,
			@RequestParam String loc,@RequestParam Long id)
	{
		Integer count=custService.updateCustomerNameAndLocationById(name, loc, id);
		return new ResponseEntity<String>(count+" customer record has updated successfully", HttpStatus.OK);
	}
	
}
