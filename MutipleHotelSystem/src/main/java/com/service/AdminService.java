package com.service;

import java.util.Optional;

import com.security.JwtUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.AdminRepo;
import com.entity.Admin;

@Service
public class AdminService {
	
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	JSONObject responseJson = new JSONObject();
	String status,msg;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	public ResponseEntity<?> deleteAdmin(long adminId) {
					
		Optional<Admin> admin = adminRepo.findById(adminId);
		if(admin.isPresent()) {
			adminRepo.deleteById(adminId);
			responseJson.put("message", "User Deleted Successfully");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseJson);
		}
		else {
			responseJson.put("error", "Invalid User ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
		
	}
	
	public ResponseEntity<?> addUsers(Admin admin) {
		
        if (adminRepo.findByUserEmail(admin.getUserEmail()) != null) {
        	responseJson.put("error", "This Email is Already Register");
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
        }
		//Encode the password
        String encodedPassword = passwordEncoder.encode(admin.getUserPassword());
        admin.setUserPassword(encodedPassword);

        Admin createdAdmin = adminRepo.save(admin);
        if (createdAdmin != null) { 
            
            responseJson.put("message", "Staff member Added");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
        } else {
            responseJson.put("error","Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
        }
    }
	
	
	public ResponseEntity getUserByEmail(String email)
	{
		if(adminRepo.findByUserEmail(email) != null)
		{
			responseJson.put("email", email);
            return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		}
		else
		{
			responseJson.put("error", "This Email is not Found");
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
		
	}




	public ResponseEntity<?> signInUser(String userEmail, String userPassword) {
		try {
			Admin existingAdmin = adminRepo.findByUserEmail(userEmail);

			if (existingAdmin == null) {
				responseJson.put("status", false);
				responseJson.put("error", "Email is Incorrect");
				responseJson.put("message", "The provide Email is Incorrect");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);

			}
			if (!passwordEncoder.matches(userPassword, existingAdmin.getUserPassword())) {
				responseJson.put("status", false);
				responseJson.put("error", "Password Mismatch");
				responseJson.put("message", "The provided password is incorrect");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
			}


			int role = existingAdmin.getRole();


			if (role == 1) {
				String jwtToken = jwtUtil.generateToken(existingAdmin);
				responseJson.put("status", true);
				responseJson.put("response", "superAdmin");
				
				responseJson.put("token", jwtToken);
				return ResponseEntity.status(HttpStatus.OK).body(responseJson);

			} else if (role ==2) {
				String jwtToken = jwtUtil.generateToken(existingAdmin);
				responseJson.put("status", true);
				responseJson.put("response", "");
				responseJson.put("hotel", "Viser");
				responseJson.put("token", jwtToken);
				return ResponseEntity.status(HttpStatus.OK).body(responseJson);

			} 
			else if (role ==3) {
				String jwtToken = jwtUtil.generateToken(existingAdmin);
				responseJson.put("status", true);
				responseJson.put("response", "manager");
				responseJson.put("token", jwtToken);
				return ResponseEntity.status(HttpStatus.OK).body(responseJson);

			}
			
			else  {
				responseJson.put("status", true);
				responseJson.put("response", "staff");

				return ResponseEntity.status(HttpStatus.OK).body(responseJson);
			}



		} catch (Exception ex) {
			responseJson.put("status", false);
			responseJson.put("error", "Error Accured");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
		}
	}

	public ResponseEntity<?> deleteByUserId(Long userId) {

		Optional<Admin> existingAdminOptional = adminRepo.findById(userId);

		if (existingAdminOptional.isPresent()) {
			Admin existingAdmin = existingAdminOptional.get();
			adminRepo.delete(existingAdmin);
			responseJson.put("status", true);
			responseJson.put("response","Member Deleted Successfully");
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		} else {
			responseJson.put("status", false);
			responseJson.put("error","Member Id doesn't Match");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}
	}


	public ResponseEntity<?> updateUserById(Long userId, Admin updatedAdmin) {
		Optional<Admin> existingAdminOptional = adminRepo.findById(userId);
		if (existingAdminOptional.isEmpty()) {
			responseJson.put("status", false);
			responseJson.put("error", "User Id is Incorrect");
			responseJson.put("message", "The provide User Id is Incorrect");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);

		}

		Admin existingAdmin = existingAdminOptional.get();

		// Check if the updated email already exists for a different user
		Admin adminWithUpdatedEmail = adminRepo.findByUserEmail(updatedAdmin.getUserEmail());
		if (adminWithUpdatedEmail != null && !adminWithUpdatedEmail.getUserEmail().equals(userId)) {
			responseJson.put("error", "This Email is Already Registered");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson);
		}

		// Update the existing admin's properties
		existingAdmin.setUserEmail(updatedAdmin.getUserEmail());
		existingAdmin.setUserName(updatedAdmin.getUserName());

		// Encode the new password if provided
		if (updatedAdmin.getUserPassword() != null && !updatedAdmin.getUserPassword().isEmpty()) {
			String encodedPassword = passwordEncoder.encode(updatedAdmin.getUserPassword());
			existingAdmin.setUserPassword(encodedPassword);
		}

		Admin updatedAdminResult = adminRepo.save(existingAdmin);
		if (updatedAdminResult != null) {
			responseJson.put("message", "Staff member Updated");
			return ResponseEntity.status(HttpStatus.OK).body(responseJson);
		} else {
			responseJson.put("error", "Server Error");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson);
		}
	}


}
