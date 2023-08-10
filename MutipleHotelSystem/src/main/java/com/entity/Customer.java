package com.entity;

import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long customerId;
	private String cusName;
	private String cusEmail;
	private String cusPhone;
	 @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	    private LocalDateTime checkInDateTime;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
		private LocalDateTime checkOutDateTime;
	private String address;
	private String city;
	private String country;
	
	private int totalguest;
	private int adults;
	private int children;
	private String decumentId;
	
	private boolean isActive;
	private int roomNo;
	private String hotelName;
	
	
	
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalguest() {
		return totalguest;
	}
	public void setTotalguest(int totalguest) {
		this.totalguest = totalguest;
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
	public String getDecumentId() {
		return decumentId;
	}
	public void setDecumentId(String decumentId) {
		this.decumentId = decumentId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", cusName=" + cusName + ", cusEmail=" + cusEmail + ", cusPhone="
				+ cusPhone + ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime=" + checkOutDateTime
				+ ", address=" + address + ", city=" + city + ", country=" + country + ", totalguest=" + totalguest
				+ ", adults=" + adults + ", children=" + children + ", decumentId=" + decumentId + ", isActive="
				+ isActive + ", roomNo=" + roomNo + "]";
	}
	public Customer(long customerId, String cusName, String cusEmail, String cusPhone, LocalDateTime checkInDateTime,
			LocalDateTime checkOutDateTime, String address, String city, String country, int totalguest, int adults,
			int children, String decumentId, boolean isActive, int roomNo) {
		super();
		this.customerId = customerId;
		this.cusName = cusName;
		this.cusEmail = cusEmail;
		this.cusPhone = cusPhone;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.address = address;
		this.city = city;
		this.country = country;
		this.totalguest = totalguest;
		this.adults = adults;
		this.children = children;
		this.decumentId = decumentId;
		this.isActive = isActive;
		this.roomNo = roomNo;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
