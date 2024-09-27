package com.gentech.customer.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "customer_email")
	private String emailId;
	
	@Column(name = "customer_location")
	private String location;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date creaetdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

}
