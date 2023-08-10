package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dao.CustomerRepo;
import com.entity.Customer;

@RestController
public class CustomerController {

	
	@Autowired
	private CustomerRepo customerRepository;
	
	@PostMapping("/addCustomer")
	public Customer saveCustomer(Customer customer)
	{
		return customerRepository.save(customer);
		
	}
	
	
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllRoom() {
		List<Customer> customer = (List<Customer>) customerRepository.findAll();
		return customer;
	}
	
}
