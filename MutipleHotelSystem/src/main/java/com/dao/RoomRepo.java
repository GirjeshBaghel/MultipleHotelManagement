package com.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.entity.Room;

@EnableJpaRepositories
@Repository
public interface RoomRepo  extends JpaRepository<Room, Integer>{
	
	Room findByroomNo(int roomNo);
	Room findByroomId(int roomId);
	List<Room> findByhotelName(String hotelName);
	Set<Room> findByRoomType(String roomType);
	List<Room> findByHotel_HotelId(Long hotelId);
	//@Query("SELECT DISTINCT r.roomType FROM Room r WHERE r.hotel.hotelId = ?1")
	@Query("SELECT r1 FROM Room r1 " +
			"INNER JOIN(" +
			"    SELECT MIN(r2.roomId) AS minRoomId " +
			"    FROM Room r2 " +
			"    WHERE r2.hotel.hotelId = ?1 " +
			"    GROUP BY r2.roomType" +
			") r3 ON r1.roomId = r3.minRoomId")
	List<Room> findDistinctRoomTypeByHotel_Id(Long hotelId);
}
