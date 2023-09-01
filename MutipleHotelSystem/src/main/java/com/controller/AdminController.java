package com.controller;


import com.dao.AdminRepo;
import com.security.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.entity.Admin;

import com.service.AdminService;

import java.util.List;

@RequestMapping("/user")
@RestController
//@SecurityRequirement(name = "mutiplehotelopenapi")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminRepo adminRepo;
	
	ResponseEntity<?> response;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/")
	public String indexpage()
	{
		return "Multiple Hotel Management System Rest API";
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addUser")
	ResponseEntity<?> createUser(@RequestBody Admin admin){

		 response = adminService.addUsers(admin);
		return response;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUser")
	public List<Admin> getAllUser()
	{
		List<Admin> admin =  adminRepo.findAll();
		return admin;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getByUserEmail/{email}")
	ResponseEntity<?> getByUserEmail(@PathVariable("email") String email){
		 response = adminService.getUserByEmail(email);
		return response;
	}
	@PreAuthorize("permitAll")
	@PostMapping("/signinMember")
	ResponseEntity<?> signInUser(@RequestParam String userEmail, @RequestParam String userPassword)
	{
		response = adminService.signInUser(userEmail,userPassword);
		return response;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/deleteById/{userId}")
	ResponseEntity<?> deleteById(@PathVariable("userId") Long userId)
	{
		response = adminService.deleteByUserId(userId);
		return response;
	}

	@PostMapping("/logout")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ","");
		jwtUtil.blacklistToken(token);
		return ResponseEntity.ok().body("Logged out successfully");
	}
	@PostMapping("/securedEndpoint")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> securedEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");

		// Check if the token is blacklisted
		if (jwtUtil.isTokenBlacklisted(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}

		// Check if the token is expired
		if (jwtUtil.isTokenExpired(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has expired");
		}

		return ResponseEntity.ok().body("Access granted");
	}


	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/updateUser/{userId}")
	//@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId , @RequestBody Admin admin)
	{
		response = adminService.updateUserById(userId,admin);
		return response;
	}

}
