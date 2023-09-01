package com.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Table
@Entity
public class BookingRequest {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookingRequestId;

    private String cusName;
    private String cusEmail;
    private String cusPhone;

    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    private String hotelName;
    private String roomType;

    private String status;


    public Long getBookingRequestId() {
        return bookingRequestId;
    }

    public void setBookingRequestId(Long bookingRequestId) {
        this.bookingRequestId = bookingRequestId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public LocalDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(LocalDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public LocalDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(LocalDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
