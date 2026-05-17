package com.codeinsight.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		System.out.println("Hello from home in console");
		return "home";
	}
}
