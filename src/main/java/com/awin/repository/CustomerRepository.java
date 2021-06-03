package com.awin.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.awin.entity.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Integer>{
public List<Customer> findByCityOrderByNameAsc(String name);
}
