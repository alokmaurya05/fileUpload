package com.awin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="PRODUCT")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    
	@Id
    @GeneratedValue
	private int id;
	private String productName;
    /* Bidirectional you can omit But it is recommended */
	@ManyToOne
	@JsonIgnore
	@Fetch(FetchMode.JOIN)
	private Customer customer;
	 
}
