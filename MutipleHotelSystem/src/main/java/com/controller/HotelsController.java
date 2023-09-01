package com.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.entity.Booking;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.dao.HotelsRepo;
import com.entity.Hotels;
import com.service.HotelsService;

@RestController
@RequestMapping("/hotels")
public class HotelsController {
	
	
	@Autowired
	private HotelsService hotelService;
	JSONObject responseJson = new JSONObject();
	
	@Autowired
	private HotelsRepo hotelsRepo;
	
	@PostMapping("/addHotel")
    public ResponseEntity<?> addHotel(@RequestParam(value = "file") MultipartFile[] files, @RequestParam("hotelName") String hotelName,
									  @RequestParam("subtile") String subtile, @RequestParam("destination") String destination,
									  @RequestParam("status") String status, @RequestParam("registerDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date registerDate, @RequestParam("hotelClass") String hotelClass,
									  @RequestParam("phoneNo") String phoneNo, @RequestParam("address") String address, @RequestParam("hotelEmail") String hotelEmail, @RequestParam("description") String description)
 	{
	try {
	     ResponseEntity<?> addHotel =hotelService.addHotel( files, hotelName,  subtile,  destination, status,  registerDate,  hotelClass,  phoneNo,  address,  hotelEmail,  description);
		 return ResponseEntity.ok(addHotel);
	 }
	catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }}

	@PutMapping("/updateHotel/{hotelId}")
	public ResponseEntity<?> updateHotel(@PathVariable("hotelId") Long hotelId, @RequestParam(value = "file") MultipartFile[] files, @RequestParam("hotelName") String hotelName,
										@RequestParam("subtile") String subtile, @RequestParam("destination") String destination,
										@RequestParam("status") String status, @RequestParam("registerDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date registerDate, @RequestParam("hotelClass") String hotelClass,
										@RequestParam("phoneNo") String phoneNo, @RequestParam("address") String address, @RequestParam("hotelEmail") String hotelEmail, @RequestParam("description") String description, HotelsController roomService) {
		try {
			ResponseEntity<?> updateHotel= roomService.updateHotel(hotelId,files,hotelName,subtile,destination,status,registerDate,hotelClass,phoneNo,address,hotelEmail,description,roomService);
			return ResponseEntity.ok(updateHotel);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/findByDestination/{locationName}")
	public ResponseEntity<?> findByHotelLocation(@PathVariable("locationName") String destination)
	{
		Set<Hotels> hotels = hotelsRepo.findByDestination(destination);
		if(hotels != null)
		{
			return ResponseEntity.ok(hotels);
		}else {
			responseJson.put("Error : ", "Hotels of this Destination Not Available");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		}

	}
	
	@GetMapping("/findByHotelName/{hotelName}")
	public ResponseEntity<?> findByHotelName(@PathVariable("hotelName") String hotelName)
	{
		 Hotels hotels = hotelsRepo.findByhotelName(hotelName);
		 if(hotels != null)
		 {
			 return ResponseEntity.ok(hotels);
		 }
		 responseJson.put("Error : ", "Hotel Name Wrong");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
	}
	
	@GetMapping("/findByHotelId/{hotelId}")
	public ResponseEntity<?> findByHotelID(@PathVariable("hotelId") Long hotelId)
	{
		 Hotels hotels = hotelsRepo.findByHotelId(hotelId);
		 if(hotels != null)
		 {
			 return ResponseEntity.ok(hotels);
		 }
		 responseJson.put("Error : ", "Hotel Id Wrong");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
	}
	
	@DeleteMapping("/deleteByHotelId/{hotelId}")
	public ResponseEntity<?> deleteByHotelID(@PathVariable("hotelId") Long hotelId) {
		Hotels hotel = hotelsRepo.findByHotelId(hotelId);
		if (hotel!= null) {
			hotelsRepo.delete(hotel);
			responseJson.put("Message", "Hotel Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseJson);
		} else {
			responseJson.put("Error : ", "Invalid Hotel Id");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
	}
	@GetMapping("/getAllHotels")
	public List<Hotels> getAllHotels()
	{
		List<Hotels>  hotels = hotelsRepo.findAll();
		return hotels;

	}

}
