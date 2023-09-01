package com.controller;

import com.entity.BookingRequest;
import com.entity.Customer;
import com.service.BookingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingRequestController {

    @Autowired
    private BookingRequestService bookingRequestService;


    @PostMapping("/bookingRequest/{hotelId}/{roomType}")
    public ResponseEntity<?> bookingRequest(@PathVariable("hotelId") Long hotelId, @PathVariable("roomType") String roomType, @RequestBody BookingRequest bookingRequest) {
       ResponseEntity<?> response =  bookingRequestService.bookingRequestService(hotelId,roomType, bookingRequest);

        return response;
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/acceptBooking/{bookingRequestId}")
    public ResponseEntity<?> acceptBooking(@PathVariable("bookingRequestId") Long bookingRequestId) {
        ResponseEntity<?> response = bookingRequestService.acceptBookingRequestService(bookingRequestId);

        return response;
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/denyBooking/{bookingRequestId}")
    public ResponseEntity<?> denyBooking(@PathVariable("bookingRequestId") Long bookingRequestId) {
        ResponseEntity<?> response = bookingRequestService.denyBookingRequestService(bookingRequestId);

        return response;
    }

}
