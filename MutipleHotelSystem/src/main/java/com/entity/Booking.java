package com.entity;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Table
@Entity
public class Booking {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long bookingId;
	
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    private String bookingType;
    private double totalprice;
    private String status;
    private int noOfperson;
    private int roomNo;
    private String roomType;
    private String hotelName;
    private String customerName;
    private String paymentStatus;
    private int totalNights;
    private int adults;
    private int children;

//	@JsonIgnore
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customerId")
//    private Customer customer;
//
//   	@JsonIgnore
//	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnoreProperties("booking")
//	private List<Room> rooms;

   	
   	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getTotalNights() {
		return totalNights;
	}

	public void setTotalNights(int totalNights) {
		this.totalNights = totalNights;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public List<Room> getRooms() {
//		return rooms;
//	}
//
//	public void setRooms(List<Room> rooms) {
//		this.rooms = rooms;
//	}


		public String getPaymentStatus() {
			return paymentStatus;
		}

		public String getHotelName() {
			return hotelName;
		}

		public void setHotelName(String hotelName) {
			this.hotelName = hotelName;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}


	public long getBookingId() {
			return bookingId;
		}

		public void setBookingId(long bookingId) {
			this.bookingId = bookingId;
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

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public int getNoOfperson() {
		return noOfperson;
	}

	public void setNoOfperson(int noOfperson) {
		this.noOfperson = noOfperson;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime="
				+ checkOutDateTime + ", bookingType=" + bookingType + ", totalprice=" + totalprice + ", noOfperson="
				+ noOfperson + ", roomNo=" + roomNo + ", nights=" + nights + ", status=" + status;
	}

	public Booking(long bookingId, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, String bookingType,
			double totalprice, int noOfperson, int roomNo, long nights, String status, Customer customer,
			List<Room> rooms) {
		super();
		this.bookingId = bookingId;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.bookingType = bookingType;
		this.totalprice = totalprice;
		this.noOfperson = noOfperson;
		this.roomNo = roomNo;
		this.nights = nights;
		this.status = status;
		
	}

    

	
	
}