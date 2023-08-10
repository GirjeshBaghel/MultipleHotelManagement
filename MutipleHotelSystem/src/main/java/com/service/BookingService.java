package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AdminRepo;
import com.dao.BookingRepo;
import com.dao.CustomerRepo;
import com.dao.HotelsRepo;
import com.dao.RoomRepo;

@Service
public class BookingService {

	@Autowired
	private RoomRepo roomRepository;
	
	@Autowired
	private BookingRepo bookingRepository;
	@Autowired
	private CustomerRepo customerRepository;
		
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private HotelsRepo hotelRepository;
	
	
	
	
	
	
	
	
	
}
