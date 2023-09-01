package com.controller;

import com.dao.BookingRepo;
import com.entity.Admin;
import com.entity.Booking;
import com.entity.BookingCreate;
import com.entity.Customer;
import com.service.BookingService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepo bookingRepo;

    ResponseEntity<?> response;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/updatebooking/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable("bookingId") Long bookingId, @RequestBody Booking booking) {
          response=bookingService.updateBooking(bookingId,booking);
        return response;
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingCreate bookingCreate) {
        Booking newBooking = bookingCreate.getBooking();
        Customer customer = bookingCreate.getCustomer();
        ResponseEntity<?> response = bookingService.createBooking(newBooking,customer);
        return response;
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @GetMapping("/getAllBooking")
    public List<Booking> getAllBooking()
    {
        List<Booking>  bookings = bookingRepo.findAll();
        return bookings;

    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/deleteBookingById/{bookingId}")
    ResponseEntity<?> deleteById(@PathVariable("bookingId") Long bookingId)
    {
        response = bookingService.deleteByUserId(bookingId);
        return response;
    }



}
