package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Exception.ResourceNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dao.HotelsRepo;
import com.entity.Hotels;
import com.entity.Room;
  
@Service
public class HotelsService {
	
	@Autowired
	private HotelsRepo hotelsRepo;
	
	@Autowired
    private AmazonS3 s3Client;
	
	@Value("${aws.bucket.name}")
    private String bucketName;
	@Autowired
	private RoomService roomService;
	JSONObject responseJson = new JSONObject();
	
	  
    private String baseUrl = "https://hotelmanagementimages.s3.ap-south-1.amazonaws.com/";
	
		
	public List<Hotels> getAllHotel() {
		List<Hotels> hotel = (List<Hotels>) hotelsRepo.findAll();
		return hotel;
	}
	
	
	
	public String deletebyIdHotel(String hotelId ) {
		String message=null;
		
		Optional<Hotels> hotel = hotelsRepo.findById(hotelId);
		if(hotel.isPresent()) {
			hotelsRepo.deleteById(hotelId);
			message=new String("Hotel deleted successfully!!");
		}
		else {
			throw new ResourceNotFoundException("Hotel"," Id",hotelId);
		}
		return message;
	}

	
	 public ResponseEntity<?> updateHotel(String hotelId, Hotels hotel, MultipartFile[] files) {
	     

	        hotel = hotelsRepo.findByHotelId(hotelId);
	        if (hotel != null) {
	        	List<String> imageUrls = new ArrayList<>();           
	        	for (MultipartFile file : files) {
			         File fileObj = roomService.convertMultiPartFileToFile(file);
			         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			         s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
			         fileObj.delete();
			         imageUrls.add(baseUrl+fileName);
			     }
	        		hotel.setHotelImageUrl(imageUrls.toArray(new String[0]));
	                hotelsRepo.save(hotel);
	            responseJson.put("Message", "Hotel Updated Successfully");
	            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
	        } else {
	            responseJson.put("Error", "Invalid Hotel ID");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
	        }
	    }

	 
	public ResponseEntity<?> addHotel(MultipartFile[] files, String hotelName, String subtile, String destination,
			String status) {
		
		 Hotels hotelExits = hotelsRepo.findByhotelName(hotelName);
		    if (hotelExits != null ) {
		        responseJson.put("error", "Hotel With this Name Already Exits!.");
		        return ResponseEntity.badRequest().body(responseJson);
		    }  
		 	Hotels hotels = new Hotels();
			 List<String> imageUrls = new ArrayList<>();
			 
			 for (MultipartFile file : files) {
		         File fileObj = roomService.convertMultiPartFileToFile(file);
		         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		         s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		         fileObj.delete();
		         imageUrls.add(baseUrl+fileName);
		     }
	        hotels.setHotelName(hotelName);
	        hotels.setStatus(status);
	        hotels.setSubtile(subtile);
	        hotels.setHotelImageUrl(imageUrls.toArray(new String[0]));
			responseJson.put("Response", hotels);
	        
			 return ResponseEntity.ok().body(responseJson);
	
	}

	
	

}
