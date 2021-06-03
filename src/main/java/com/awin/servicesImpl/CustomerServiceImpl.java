package com.awin.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.awin.entity.Customer;
import com.awin.repository.CustomerRepository;
import com.awin.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
	@Autowired
	private CustomerRepository customerRepository ;
	
	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public Optional<Customer> showCustomer(int customerid) {
		
		return customerRepository.findById(customerid);
	}

	@Override
	public List<Customer> showCustomerByCity(String customerName) {
		
		return customerRepository.findByCityOrderByNameAsc(customerName);
	}

}
