package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dao.RoomRepo;
import com.entity.Room;

@Service
public class RoomService {

	@Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;
    @Autowired 
	private RoomRepo roomRepository;
	
    
    private String baseUrl = "https://hotelmanagementimages.s3.ap-south-1.amazonaws.com/";
	
	JSONObject responseJson = new JSONObject();
	
	
	 public ResponseEntity<?> addRoom(MultipartFile[] files, int roomId,
	             String title,  boolean isActive,Long pricePerDay, int bed, String hotelName, int maxPeople, int roomNo) 
	 { 
		 Room roomExists = roomRepository.findByroomNo(roomNo);
		    if (roomExists != null ) {
		        responseJson.put("error", "Room with the given room number already exists.");
		        return ResponseEntity.badRequest().body(responseJson);
		    }
		 	Room room = new Room();
		 	 List<String> imageUrls = new ArrayList<>();
		 	 
		 	 for (MultipartFile file : files) {
		         File fileObj = convertMultiPartFileToFile(file);
		         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		         s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		         fileObj.delete();
		         imageUrls.add(baseUrl+fileName);
		     }
	        room.setRoomNo(roomNo);
	        room.setRoomId(roomId);
	        room.setTitle(title);
			room.setBed(bed);
			room.setHotelName(hotelName);
			room.setActive(isActive);
			room.setMaxPeople(maxPeople);
			room.setRoomImageUrl(imageUrls.toArray(new String[0])); 
			roomRepository.save(room);
			responseJson.put("Response", room);
	        
			 return ResponseEntity.ok().body(responseJson);
	    }
	
	 File convertMultiPartFileToFile(MultipartFile file) {
	        File convertedFile = new File(file.getOriginalFilename());
	        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
	            fos.write(file.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return convertedFile;
	    }
		
		
	 public ResponseEntity<?> deleteByRoomNo(int roomNo) {
	        
		Room  room = roomRepository.findByroomNo(roomNo);
			if(room != null) {
				roomRepository.delete(room);
				responseJson.put("Message", "Room Deleted Successfully");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseJson);
			}
			else {
				responseJson.put("Error : ", "Inavalid Room No");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
			}
	 }


	
	public ResponseEntity<?> updateRoom(int roomNo, Room room,MultipartFile[] files) {
		
		  room = roomRepository.findByroomNo(roomNo);
		if(room != null) {
			List<String> imageUrls = new ArrayList<>();
			room.setBed(room.getBed());
			room.setMaxPeople(room.getMaxPeople());
			room.setTitle(room.getTitle());
			room.setTitle(room.getTitle());
			room.setPricePerDay(room.getPricePerDay());
			room.setActive(room.isActive());
			if (files != null) {
				
				 for (MultipartFile file : files) {
			         File fileObj = convertMultiPartFileToFile(file);
			         String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			         s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
			         fileObj.delete();
			         imageUrls.add(baseUrl+fileName);
			     }
			 }
			room.setRoomImageUrl(imageUrls.toArray(new String[0]));
			 roomRepository.save(room);
			 responseJson.put("Message", "Room Updated Successfully");
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);
			
		}
		else {
			responseJson.put("Error : ", "Invalid Room No");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
				
	}
	

}
