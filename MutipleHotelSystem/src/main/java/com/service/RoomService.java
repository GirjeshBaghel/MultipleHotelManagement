package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dao.HotelsRepo;
import com.entity.Hotels;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired(required=true)
	private HotelsRepo hotelsRepo;
	
    
    private String baseUrl = "https://hotelmanagementimages.s3.ap-south-1.amazonaws.com/";
	
	JSONObject responseJson = new JSONObject();
	
	
	 public ResponseEntity<?> addRoom(Long hotelId,MultipartFile[] files, String title, boolean isActive,
									   Long pricePerDay, int bed,
									   String hotelName,  int maxPeople,
									   int roomNo,String roomType,  String description,
									  String releas, String[] facilities)
	 {
		 Hotels hotel = hotelsRepo.findByHotelId(hotelId);
		 if(hotel == null)
		 {
			 responseJson.put("error", "Hotel Id is Wrong !..");
			 return ResponseEntity.badRequest().body(responseJson);
		 }
		 Room roomExists = roomRepository.findByroomNo(roomNo);
		    if (roomExists != null ) {
		        responseJson.put("error", "Room with the given room number already exists.");
		        return ResponseEntity.badRequest().body(responseJson);
		    }
		 	Room room = new Room();
		 	 List<String> imageUrls = new ArrayList<>();
		 	 
		 	 for (MultipartFile file : files) {
		         File fileObj = convertMultiPartFileToFile(file);
		         String fileName = "RoomImages/"+System.currentTimeMillis() + "_" + file.getOriginalFilename();
		         s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		         fileObj.delete();
		         imageUrls.add(baseUrl+fileName);
		     }
			  room.setHotel(hotel);
	        room.setRoomNo(roomNo);
	        room.setTitle(title);
			room.setBed(bed);
			room.setHotelName(hotelName);
			room.setActive(isActive);
			room.setMaxPeople(maxPeople);
			room.setRoomType(roomType);
			room.setPricePerDay(pricePerDay);
			room.setDescription(description);
			room.setFacilities(facilities);
			room.setReleas(releas);
			room.setRoomImageUrl(imageUrls.toArray(new String[0]));
			roomRepository.save(room);
			//responseJson.put("Response", room);
	        
			 return ResponseEntity.ok().body("Room Added Successfully");
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
				responseJson.put("Error : ", "Invalid Room No");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
			}
	 }


	public ResponseEntity<?> updateRoom(Long hotelId, int roomNo, MultipartFile[] files, String title, boolean isActive, Long pricePerDay, int bed, String hotelName, int maxPeople, String roomType, String description, String releas, String[] facilities) {
		Hotels hotel = hotelsRepo.findByHotelId(hotelId);
		if(hotel == null)
		{
			responseJson.put("error", "Hotel Id is Wrong !..");
			return ResponseEntity.badRequest().body(responseJson);
		}

		Room  room = roomRepository.findByroomNo(roomNo);
		if(room != null) {
			List<String> imageUrls = new ArrayList<>();
			room.setTitle(title);
			room.setBed(bed);
			room.setHotelName(hotelName);
			room.setActive(isActive);
			room.setMaxPeople(maxPeople);
			room.setRoomType(roomType);
			room.setPricePerDay(pricePerDay);
			room.setDescription(description);
			room.setFacilities(facilities);
			room.setReleas(releas);

			if (files != null) {
				
				 for (MultipartFile file : files) {
			         File fileObj = convertMultiPartFileToFile(file);
			         String fileName = "RoomImages/"+System.currentTimeMillis() + "_" + file.getOriginalFilename();
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
