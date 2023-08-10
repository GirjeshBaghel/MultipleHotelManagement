package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


@Table
@Entity
public class Room {

	@Id
	private int roomId;
	private int roomNo;
    private int bed;
    private String title;
    private String hotelName;
    private String[] roomImageUrl;
    private int maxPeople;
    private Long pricePerDay;
    private boolean isActive;
    private String roomType;
    private String description;
    private String[] facilities;
    private String release;
    
    
    
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getFacilities() {
		return facilities;
	}
	public void setFacilities(String[] facilities) {
		this.facilities = facilities;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String[] getRoomImageUrl() {
		return roomImageUrl;
	}
	public void setRoomImageUrl(String[] roomImageUrl) {
		this.roomImageUrl = roomImageUrl;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public int getBed() {
		return bed;
	}
	public void setBed(int bed) {
		this.bed = bed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public Long getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(Long pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNo=" + roomNo + ", bed=" + bed + ", title=" + title +  ", maxPeople=" + maxPeople + ", pricePerDay=" + pricePerDay + ", isActive=" + isActive
				+ "]";
	}
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
    
    
}
