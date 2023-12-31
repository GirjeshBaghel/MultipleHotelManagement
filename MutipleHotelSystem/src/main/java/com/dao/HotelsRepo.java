package com.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Hotels;

@Repository
public interface HotelsRepo  extends JpaRepository<Hotels, String>{

		Hotels findByhotelName(String hotelName);
		
		Hotels findByHotelId(Long hotelId);
	
		Set<Hotels> findByDestination(String destination);
}
