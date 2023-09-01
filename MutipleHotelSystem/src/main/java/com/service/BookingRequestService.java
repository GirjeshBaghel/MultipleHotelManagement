package com.service;

import com.dao.*;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingRequestService {


    @Autowired
    private HotelsRepo hotelRepository;

    @Autowired
    private CustomerRepo customerRepository;
    @Autowired
    private RoomRepo roomRepository;
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private BookingRequestRepo  bookingRequestRepo;

       public ResponseEntity<?> bookingRequestService(Long hotelId, String roomType,BookingRequest booking) {

        Hotels hotel = hotelRepository.findByHotelId(hotelId);
        Set<Room> room = roomRepository.findByRoomType(roomType);

        if(hotel == null || room == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("InValid hotelId");
        }
        String hotelName =  hotel.getHotelName();
        booking.setHotelName(hotelName);
        booking.setRoomType(roomType);
        booking.setStatus("Pending");
        bookingRequestRepo.save(booking);

        return ResponseEntity.ok().body("Request Send Successfully");
    }


    public ResponseEntity<?> acceptBookingRequestService(Long bookingRequestId) {

      //  BookingRequest bookingRequest = new BookingRequest();
        Optional<BookingRequest> request = bookingRequestRepo.findById(bookingRequestId);
        Booking booking = new Booking();
        Customer customer = new Customer();

        if(request.isPresent())
        {
            BookingRequest bookingRequest = request.get();
            Set<Room> room =  roomRepository.findByRoomType(bookingRequest.getRoomType());
            long noOfNights  = Duration.between(bookingRequest.getCheckInDateTime(),  bookingRequest.getCheckOutDateTime()).toDays();
         //   double totalPrice = noOfNights *room.getPricePerDay();
           // booking.setTotalprice(totalPrice);
            booking.setTotalNights((int) noOfNights);

           booking.setCustomerName(bookingRequest.getCusName());
            booking.setHotelName(bookingRequest.getHotelName());
          //  booking.setRoomNo(bookingRequest.getRoomNo());
            booking.setCheckInDateTime(bookingRequest.getCheckInDateTime());
            booking.setCheckOutDateTime(bookingRequest.getCheckOutDateTime());
            booking.setRoomType(bookingRequest.getRoomType());
            booking.setCustomerEmail(bookingRequest.getCusEmail());
            booking.setCustomerPhone(bookingRequest.getCusPhone());
            booking.setStatus("Confirm");
            bookingRepo.save(booking);
            customer.setHotelName(bookingRequest.getHotelName());
            customer.setCusEmail(bookingRequest.getCusEmail());
            customer.setCusName(bookingRequest.getCusName());
            customer.setCheckInDateTime(bookingRequest.getCheckInDateTime());
            customer.setCusPhone(bookingRequest.getCusPhone());

            EmailService emailService = new EmailService("girjeshbaghel62586@gmail.com", "xavljjywsnffwrcp","smtp.gmail.com", "587");
            emailService.sendBookingConfirmationEmail(customer.getCusEmail(), booking); // Send booking confirmation email
          bookingRequest.setStatus("Confirm");
          customerRepository.save(customer);
          bookingRequestRepo.save(bookingRequest);
          return ResponseEntity.status(HttpStatus.ACCEPTED).body("Booking Accepted");
       }
        else {
            return ResponseEntity.badRequest().body("Invalid Booking Request Id");
        }
    }

    public ResponseEntity<?> denyBookingRequestService(Long bookingRequestId) {
           BookingRequest request = bookingRequestRepo.findByBookingRequestId(bookingRequestId);
        if(request != null)
        {
           bookingRequestRepo.delete(request);
           return ResponseEntity.ok().body("Booking Cancel Confirm");
        }
        else {
            return ResponseEntity.badRequest().body("Invalid Booking Request Id");
        }

    }
}
