package com.codeinsight.exercise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.codeinsight.exercise.DTO.FoodItemDTO;
import com.codeinsight.exercise.DTO.UserDTO;
import com.codeinsight.exercise.service.FoodItemService;
import com.codeinsight.exercise.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	@Autowired
	FoodItemService foodItemService;
	
	@GetMapping("/")
	String login() {
		return "Hello World From Login Page";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserDTO userDTO) {
		System.out.println(userDTO.toString());
		userService.registerUser(userDTO);
		
		return "User Registered Successfully";
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
