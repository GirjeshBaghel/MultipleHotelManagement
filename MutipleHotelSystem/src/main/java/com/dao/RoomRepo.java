package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.entity.Room;

@EnableJpaRepositories
@Repository
public interface RoomRepo  extends JpaRepository<Room, Integer>{
	
	Room findByroomNo(int roomNo);

	List<Room> findByhotelName(String hotelName);
}
