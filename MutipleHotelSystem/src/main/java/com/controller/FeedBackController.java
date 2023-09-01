package com.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.FeedBackRepository;
import com.entity.FeedBack;

@RestController
public class FeedBackController {

    @Autowired
    private FeedBackRepository backRepository;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @PostMapping("/createFeedBack")
    ResponseEntity<?> feedBack(@RequestBody FeedBack feedBack){

        backRepository.save(feedBack);
        return ResponseEntity.ok(HttpStatus.CREATED).ok("Feed Back Send Successfully");
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
    @GetMapping("/getAllFeedBack")
    public ResponseEntity<?> getAllFeedBack(){

        FeedBack back =  (FeedBack) backRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(back);
    }

    @GetMapping("/")
    public String  indexFile()
    {
        return "This is Multiple Hotel  Backend API ";
    }

}
