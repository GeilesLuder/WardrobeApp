package com.example.wardrobeapp.controller;

import com.example.wardrobeapp.model.Customer;
import com.example.wardrobeapp.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;



@RestController
public class CustomerController {

  @Autowired
  private CustomerService customerService;


  // In general we should not send the password unhashed through the API but we dont have time for proper security
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody Customer customer){
      if (customerService.loginUser(customer.getEmail(), customer.getPassword())) {
        return new ResponseEntity<String>("Login successful", HttpStatus.OK);
      } else {
        return new ResponseEntity<String>("Login failed", HttpStatus.UNAUTHORIZED);
      }
  }

  
}