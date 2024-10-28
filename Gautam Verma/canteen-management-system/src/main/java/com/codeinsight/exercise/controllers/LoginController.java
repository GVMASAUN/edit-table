package com.codeinsight.exercise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.codeinsight.exercise.DTO.FoodItemDTO;
import com.codeinsight.exercise.DTO.ResponseDTO;
import com.codeinsight.exercise.DTO.UserDTO;
import com.codeinsight.exercise.service.FoodItemService;
import com.codeinsight.exercise.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class LoginController{
	
	@Autowired
	UserService userService;
	@Autowired
	FoodItemService foodItemService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO userDTO) {
		System.out.println("Hello from login " + userDTO.toString());
		return ResponseEntity.ok(userService.login(userDTO, authenticationManager));
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
		System.out.println(userDTO.toString());
		return ResponseEntity.ok(userService.registerUser(userDTO, passwordEncoder));
	}
	
	@GetMapping("/item")
	public ResponseEntity<ResponseDTO> getFoodItems(){
		return ResponseEntity.ok(foodItemService.getAllFoodItems());
	}
	
	@PostMapping("/item")
	public String addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
		foodItemService.createFoodItem(foodItemDTO);
		return "Food Item Added Successfully";
	}
	
	@PutMapping("/item")
	public String updateFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
		foodItemService.updateItem(foodItemDTO);
		return "Food Item updated Successfully";
	}
}
