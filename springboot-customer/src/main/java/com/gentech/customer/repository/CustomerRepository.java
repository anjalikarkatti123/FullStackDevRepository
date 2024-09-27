package com.gentech.customer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gentech.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long>{

	List<Customer> findByCustomerName(String name);
	
	List<Customer> findByLocation(String name);
	
	List<Customer> findByCustomerNameAndLocation(String name, String loc);
	
	List<Customer> findByCustomerNameContaining(String partialName);
	
	@Query("FROM Customer WHERE customerName=:cname OR location=:loc")
	List<Customer> getAllCustomersByNameAndLocation(@Param("cname") String name,String loc);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Customer WHERE customerName = :name")
	Integer getDeleteByCustomerName(String name);
	
	@Transactional
	@Modifying
	@Query("UPDATE Customer c SET c.emailId=:email WHERE c.customerName = :name")
	Integer updateEmailIdByCustomerName(String email,String name);
	
	@Transactional
	@Modifying
	@Query("update Customer c set c.customerName=:name, c.location=:loc where c.id=:id")
	Integer updateCustomerNameAndLocationById(String name,String loc,Long id);
}
