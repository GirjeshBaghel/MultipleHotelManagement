package com.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


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

	private String customerEmail;
	private String customerPhone;
    private String paymentStatus;
    private int totalNights;

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "booking_hotel",
			joinColumns = { @JoinColumn(name = "booking_id") },
			inverseJoinColumns = { @JoinColumn(name = "hotel_id") }
	)
	private Set<Hotels> hotels = new HashSet<>();


	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;


	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Set<Hotels> getHotels() {
		return hotels;
	}

	public void setHotels(Set<Hotels> hotels) {
		this.hotels = hotels;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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
				+ checkOutDateTime + ", bookingType=" + bookingType + ", totalprice=" + totalprice + ", status="
				+ status + ", noOfperson=" + noOfperson + ", roomNo=" + roomNo + ", roomType=" + roomType
				+ ", hotelName=" + hotelName + ", customerName=" + customerName + ", paymentStatus=" + paymentStatus
				+ ", totalNights=" + totalNights;
	}

	public Booking(long bookingId, LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime, String bookingType,
			double totalprice, String status, int noOfperson, int roomNo, String roomType, String hotelName,
			String customerName, String paymentStatus, int totalNights) {
		super();
		this.bookingId = bookingId;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.bookingType = bookingType;
		this.totalprice = totalprice;
		this.status = status;
		this.noOfperson = noOfperson;
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.hotelName = hotelName;
		this.customerName = customerName;
		this.paymentStatus = paymentStatus;
		this.totalNights = totalNights;

	}

		
	
}