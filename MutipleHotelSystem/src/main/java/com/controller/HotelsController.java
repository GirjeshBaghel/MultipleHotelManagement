package com.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dao.HotelsRepo;
import com.entity.Hotels;
import com.service.HotelsService;

@RestController
public class HotelsController {
	
	
	@Autowired
	private HotelsService hotelService;
	JSONObject responseJson = new JSONObject();
	
	@Autowired
	private HotelsRepo hotelsRepo;
	
	@PostMapping("/addRoom")
    public ResponseEntity<?> addHotel(@RequestParam(value = "file") MultipartFile[] files, @RequestParam("hotelName") String hotelName,
            @RequestParam("subtile") String subtile, @RequestParam("destination") String destination,
            @RequestParam("status") String status) 
 {
try {
	     ResponseEntity<?> addHotel =hotelService.addHotel(files, hotelName,subtile,destination, status);
		 return ResponseEntity.ok(addHotel);
	 }
	catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }}
	
	
	@GetMapping("/findByDestination/{locationName}")
	public ResponseEntity<?> findByHotelLocation(@PathVariable("locationName") String destination)
	{
		 List<Hotels> hotels = hotelsRepo.findByDestination(destination);
		 if(hotels != null)
		 {
			 return ResponseEntity.ok(hotels);
		 }
		 responseJson.put("Error : ", "Hotels of this Destination Not Available");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
	}
	
	
 
	
	

}
