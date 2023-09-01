package com.service;

import com.entity.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.AdminRepo;
import com.dao.BookingRepo;
import com.dao.CustomerRepo;
import com.dao.HotelsRepo;
import com.dao.RoomRepo;

import java.time.Duration;
import java.util.Collections;
import java.util.Optional;

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
	JSONObject responseJson = new JSONObject();

	public ResponseEntity<?> updateBooking(Long bookingId,Booking updatedBooking) {
	 Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		if (optionalBooking.isPresent()) {
			Booking booking = optionalBooking.get();
			booking.setBookingType(updatedBooking.getBookingType());
			booking.setStatus("Confirm");
			booking.setRoomNo(updatedBooking.getRoomNo());

			Customer updatedCustomer = updatedBooking.getCustomer();
			if (updatedCustomer != null) {
				Customer customer = booking.getCustomer();
				if (customer == null) {
					customer = new Customer();
					customer.setBookings(Collections.singletonList(booking));
				}
				customer.setAddress(updatedBooking.getCustomer().getAddress());
				customer.setCity(updatedBooking.getCustomer().getCity());
				customer.setCountry(updatedBooking.getCustomer().getCountry());
				//customer.setRoomType(updatedBooking.getCustomer().getRoomType());
				customer.setTotalguest(updatedBooking.getCustomer().getTotalguest());
				customer.setAdults(updatedBooking.getCustomer().getAdults());
				customer.setChildren(updatedBooking.getCustomer().getChildren());
				customer.setDocumentId(updatedBooking.getCustomer().getDocumentId());
				customerRepository.save(customer);
				booking.setCustomer(customer);
			}
			bookingRepository.save(booking);

			responseJson.put("status", "Ok");
			responseJson.put("message", "Booking Created Successfully.");
			return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
		} else {
			responseJson.put("error","Invalid Booking Id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		}


	}
	public ResponseEntity<String> createBooking(Booking booking , Customer customer) {
		//Booking newBooking = new Booking();
		booking.setCustomer(customer);

		customer.setCheckInDateTime(booking.getCheckInDateTime());
		customer.setCheckOutDateTime(booking.getCheckOutDateTime());
		customer.setRoomNo(booking.getRoomNo());
		customer.setHotelName(booking.getHotelName());
		customer.setRoomType(booking.getRoomType());
		customer.setTotalguest((customer.getAdults())+(customer.getChildren()));
		int numberOfNights = (int) Duration.between(booking.getCheckInDateTime(), booking.getCheckOutDateTime()).toDays();
		booking.setTotalNights(numberOfNights);

		booking.setCustomerName(customer.getCusName());
		booking.setCustomerEmail(customer.getCusEmail());
		booking.setCustomerPhone(customer.getCusPhone());
		booking.setNoOfperson(customer.getTotalguest());
		customer.setActive(true);
		Room room = roomRepository.findByroomNo(booking.getRoomNo());
		double roomprice =  room.getPricePerDay();
		booking.setTotalprice(roomprice * numberOfNights);
		EmailService emailService = new EmailService("girjeshbaghel62586@gmail.com", "xavljjywsnffwrcp","smtp.gmail.com", "587");
		emailService.sendBookingConfirmationEmail(customer.getCusEmail(), booking);

		Customer savedCustomer = customerRepository.save(customer);
		// Now, save the booking
		Booking savedBooking = bookingRepository.save(booking);

		if (savedBooking != null && savedCustomer != null) {
			responseJson.put("status","true");
			responseJson.put("message","Booking Creating Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create booking.");
		}
	}


	public ResponseEntity<?> deleteByUserId(Long bookingId) {
		Optional<Booking> booking = bookingRepository.findById(bookingId);
		if(booking.isPresent()) {
			bookingRepository.deleteById(bookingId);
			responseJson.put("message", "Booking Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseJson);
		}
		else {
			responseJson.put("error", "Invalid User ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
	}
}
