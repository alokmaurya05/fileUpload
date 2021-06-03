package com.awin.services;

import java.util.List;
import java.util.Optional;


import com.awin.entity.Customer;

public interface CustomerService {
	
public void saveCustomer(Customer customer);
public Optional<Customer> showCustomer(int customerid);
public List<Customer> showCustomerByCity(String customerName) ;
}
