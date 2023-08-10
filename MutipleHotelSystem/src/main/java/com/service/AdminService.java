package com.service;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Exception.ResourceNotFoundException;
import com.dao.AdminRepo;
import com.entity.Admin;
import com.entity.ApiResponse;

@Service
public class AdminService {
	
	
	@Autowired
	private AdminRepo adminRepo;
	
//	@Autowired
//	private PasswordEnco passwordEncoder;
//	
	JSONObject responseJson = new JSONObject();
	String status,msg;
	
	
	public ResponseEntity<?> deleteAdmin(long adminId) {
					
		Optional<Admin> admin = adminRepo.findById(adminId);
		if(admin.isPresent()) {
			adminRepo.deleteById(adminId);
			responseJson.put("Message", "User Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseJson);
		}
		else {
			responseJson.put("Error : ", "Inavalid User ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
		
	}
	
	public ResponseEntity<?> addUsers(Admin admin) {
		
        if (adminRepo.findByUserEmail(admin.getUserEmail()) != null) {
        	responseJson.put("Error", "This Email is Already Register");
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
        }

        // Encode the password
       // String encodedPassword = passwordEncoder.encode(admin.getUserPassword());
        //admin.setUserPassword(encodedPassword);

        Admin createdAdmin = adminRepo.save(admin);
        if (createdAdmin != null) { 
            
            responseJson.put("Message", "Staff member Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
        } else {
            responseJson.put("Error ","Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
        }
    }
	
	
	public ResponseEntity getUserByEmail(String email)
	{
		if(adminRepo.findByUserEmail(email) != null)
		{
			responseJson.put("Email", email);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		}
		else
		{
			responseJson.put("Error", "This Email is not Found");
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
		
	}
	
	

}
