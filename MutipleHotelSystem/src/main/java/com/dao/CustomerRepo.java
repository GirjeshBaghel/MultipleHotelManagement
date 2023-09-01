package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo  extends JpaRepository<Customer, Long>{

     Optional<Customer> findByCusEmail(String cusEmail);
     List<Customer> findByCusName(String cusName);

	
}
