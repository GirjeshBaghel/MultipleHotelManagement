package com.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Admin;
import com.entity.Room;
import com.service.AdminService;

@RequestMapping("/user")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("addUser")
	ResponseEntity<Admin> createUser(@RequestBody Admin admin){
		
		return new ResponseEntity<Admin>(HttpStatus.CREATED);
	}
	
	
	

}
