package com.awin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CUSTOMER")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
   
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String city ;
	/*
	 * mappedBy: is relationship owner of managing Key 
	 * targetEntity: is apply one to many relation on product CascadeType: what all operation need to perform on child entity (product) 
	 * orphanRemoval: if parent is remove child is also be remove when it is set true
	 * FetchType: When you want to load product while loading customer, here we are not loading product 
	 */
	
	@OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private List<Product> products ;
}
