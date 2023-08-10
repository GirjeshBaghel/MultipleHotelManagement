package com.entity;

import java.sql.Blob;
import java.util.Date;

import javax.xml.crypto.Data;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Hotels {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private String hotelId;
	private String hotelName;
	private String subtile;
	private String destination;
	private String status;
	private String[] hotelImageUrl;
	private Date registerDate;
	private String hotelClass;
	private String phoneNo;
	private String address;
	private String hotelEmail;
	private String description;
	
	
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotelEmail() {
		return hotelEmail;
	}

	public void setHotelEmail(String hotelEmail) {
		this.hotelEmail = hotelEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHotelClass() {
		return hotelClass;
	}

	public void setHotelClass(String hotelClass) {
		this.hotelClass = hotelClass;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String[] getHotelImageUrl() {
		return hotelImageUrl;
	}

	public void setHotelImageUrl(String[] hotelImageUrl) {
		this.hotelImageUrl = hotelImageUrl;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getSubtile() {
		return subtile;
	}

	public void setSubtile(String subtile) {
		this.subtile = subtile;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Hotels [hotelId=" + hotelId + ", hotelName=" + hotelName + ", subtile=" + subtile + ", destination="
				+ destination + ", status=" + status + ", image=" + "]";
	}

	public Hotels(String hotelId, String hotelName, String subtile, String destination, String status, Blob hotelImage) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.subtile = subtile;
		this.destination = destination;
		this.status = status;
		
	}

	public Hotels() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
