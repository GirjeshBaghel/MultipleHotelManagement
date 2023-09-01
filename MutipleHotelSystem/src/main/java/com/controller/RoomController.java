package com.controller;



import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.dao.HotelsRepo;
import com.entity.Hotels;
import io.swagger.v3.oas.annotations.Operation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.dao.RoomRepo;
import com.entity.Room;
import com.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private HotelsRepo hotelsRepo;
	JSONObject responseJson = new JSONObject();



	@Operation(summary = "Add a new room")
	 @PostMapping("/roomAdd/{hotelId}")
	    public ResponseEntity<?> createRoom(@PathVariable("hotelId") Long hotelId, @RequestParam(value = "file") MultipartFile[] file,
	            @RequestParam("title") String title, @RequestParam("active") boolean isActive,
	            @RequestParam("roomprice") Long pricePerDay, @RequestParam("bed") int bed,
	            @RequestParam("hotelName") String hotelName, @RequestParam("maxpeople") int maxPeople,
	            @RequestParam("roomNo") int roomNo,@RequestParam("roomType") String roomType,@RequestParam("description") String description,
				@RequestParam("releas") String releas,@RequestParam("facilities") String[] facilities)
	 {
		 ResponseEntity<?> addRoom =roomService.addRoom(hotelId,file, title,isActive,pricePerDay,bed,hotelName,maxPeople,roomNo,roomType,description,releas,facilities);
		 return ResponseEntity.ok(addRoom);
		 }

	@PutMapping("/rooms/{hotelId}/{roomNo}")
	public ResponseEntity<?> updateRoom(
			@PathVariable Long hotelId,
			@PathVariable int roomNo,
			@RequestParam(value = "file", required = false) MultipartFile[] file,
			@RequestParam("title") String title,
			@RequestParam("active") boolean isActive,
			@RequestParam("roomprice") Long pricePerDay,
			@RequestParam("bed") int bed,
			@RequestParam("hotelName") String hotelName,
			@RequestParam("maxpeople") int maxPeople,
			@RequestParam("roomType") String roomType,
			@RequestParam("description") String description,
			@RequestParam("releas") String releas,
			@RequestParam("facilities") String[] facilities
	) {
		ResponseEntity<?> updateRoom = roomService.updateRoom(hotelId,
				roomNo, file, title, isActive, pricePerDay, bed, hotelName, maxPeople,
				roomType, description, releas, facilities
		);
		return ResponseEntity.ok(updateRoom);
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


	    @GetMapping("/getAllRoomByHotelId/{hotelId}")
	    public ResponseEntity<List<Room>> getAllRooms(@PathVariable Long hotelId)
		{
			Hotels hotel = hotelsRepo.findByHotelId(hotelId);
			if(hotel != null)
			{
				List<Room> rooms = roomRepo.findAll();
				return ResponseEntity.ok(rooms);
			}
			else {
				responseJson.put("error", "Invalid Hotel Id");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((List<Room>) responseJson);
			}

	    }

	    @GetMapping("/getByRoomNo/{roomNo}")
		public ResponseEntity<?> getByRoomNo(@PathVariable("roomNo") int roomNo) {
		    Room rooms = roomRepo.findByroomNo(roomNo);
		    if (rooms == null) {
		        responseJson.put("error", "This is not Exits !");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		    } else {
		    	 return ResponseEntity.ok(rooms);
		    }
		}

	    @GetMapping("/getByHotelName/{hotelName}")
		public ResponseEntity<?> getByHotelName(@PathVariable("hotelName") String hotelName) {
		    List<Room> rooms = roomRepo.findByhotelName(hotelName);
		    if (rooms == null) {
		        responseJson.put("error", "Wrong Hotel Name !");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		    } else {
		    	 return ResponseEntity.ok(rooms); 
		    }
		}

	@GetMapping("/getByRoomType/{roomType}")
	public ResponseEntity<?> getByRoomType(@PathVariable("roomType") String roomType) {
		Set<String> uniqueRoomTypes = new HashSet<>();
		Set<Room> uniqueRooms = new HashSet<>();
		Set<Room> rooms = roomRepo.findByRoomType(roomType);
		if (rooms == null) {
			responseJson.put("error", "Wrong Choice !");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		} else {
			for (Room room : rooms) {
				String currentRoomType = room.getRoomType();
				if (!uniqueRoomTypes.contains(currentRoomType)) {
					uniqueRoomTypes.add(currentRoomType);
					uniqueRooms.add(room);
				}
			}
			return ResponseEntity.ok(uniqueRooms);
		}
	}

	@GetMapping("/getAllRooms")
	public List<Room> getAllRooms()
	{
		List<Room>  rooms = roomRepo.findAll();
		return rooms;

	}

	@GetMapping("/getAllUniqueRoom/{hotelId}")
	public ResponseEntity<?> getAllUniqueRoomTypes(@PathVariable("hotelId") Long hotelId) {
		List<Room>  uniqueRoomTypes = roomRepo.findDistinctRoomTypeByHotel_Id(hotelId);

		if (uniqueRoomTypes.isEmpty()) {
			responseJson.put("message", "No unique room types found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		} else {
			return ResponseEntity.ok(uniqueRoomTypes);
		}
	}




	@GetMapping("/getByHotelId/{hotelId}")
		public ResponseEntity<?> getByHotelId(@PathVariable("hotelId") Long hotelId) {
		   List<Room> rooms = roomRepo.findByHotel_HotelId(hotelId);
		    if (rooms == null) {
		        responseJson.put("error", "Wrong Choice !");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson);
		    } else {
		    	 return ResponseEntity.ok(rooms);
		    }
		}

}
