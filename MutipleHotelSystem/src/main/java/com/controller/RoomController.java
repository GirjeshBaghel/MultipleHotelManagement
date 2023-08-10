package com.controller;



import java.util.List;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dao.RoomRepo;
import com.entity.Room;
import com.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomRepo roomRepo;
	JSONObject responseJson = new JSONObject();
	
	
	 @PostMapping("/roomAdd")
	    public ResponseEntity<?> createRoom(@RequestParam(value = "file") MultipartFile[] file, @RequestParam("roomId") int roomId,
	            @RequestParam("title") String title, @RequestParam("active") boolean isActive,
	            @RequestParam("roomprice") Long pricePerDay, @RequestParam("bed") int bed,
	            @RequestParam("hotelName") String hotelName, @RequestParam("maxpeople") int maxPeople,
	            @RequestParam("roomNo") int roomNo) 
	 {
		 ResponseEntity<?> addRoom =roomService.addRoom(file, roomId,title,isActive, pricePerDay,bed,hotelName,maxPeople,roomNo);
		 return ResponseEntity.ok(addRoom);
//	try {
//		     
//		 }
//		catch (Exception e) {
//	        e.printStackTrace();    
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//			     }
		 }
 
	 
		  @PutMapping("/updateRoom/{roomNo}")
	    public ResponseEntity<?> updateRoom(@PathVariable("roomNo") int roomNo, @RequestBody Room room, MultipartFile[] files) {
	        try {
	            ResponseEntity<?> updatedRoom = roomService.updateRoom(roomNo, room, files);
	            return ResponseEntity.ok(updatedRoom);
	       
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	  
	  
	 
	    @DeleteMapping("/deleteByRoomNo/{roomNo}")
	    public ResponseEntity<?> deleteRoom(@PathVariable("roomNo") int roomNo) {
	        try {
	            ResponseEntity<?> message = roomService.deleteByRoomNo(roomNo);
	            return ResponseEntity.ok(message);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	  
	    @GetMapping("/getAllRoom")
	    public ResponseEntity<List<Room>> getAllRooms() {
	        List<Room> rooms = roomRepo.findAll();
	        return ResponseEntity.ok(rooms);
	    }

	  
	    @GetMapping("/getByRoomNo/{roomNo}")
		public ResponseEntity<?> getByRoomNo(@PathVariable("roomNo") int roomNo) {
		    Room rooms = roomRepo.findByroomNo(roomNo);
		    if (rooms == null) {
		        responseJson.put("Error : ", "This is not Exits !");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		    } else {
		    	 return ResponseEntity.ok(rooms);
		    }
		}
		
	    @GetMapping("/getByHotelName/{hotelName}")
		public ResponseEntity<?> getByRoomNo(@PathVariable("hotelName") String hotelName) {
		    List<Room> rooms = roomRepo.findByhotelName(hotelName);
		    if (rooms == null) {
		        responseJson.put("Error : ", "Wrong Hotel Name !");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		    } else {
		    	 return ResponseEntity.ok(rooms);
		    }
		}
	    
	  
	   

}
