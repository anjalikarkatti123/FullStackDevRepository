package com.gentech.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gentech.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
