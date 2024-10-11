package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.SecurityUserDetailService;

@RestController
public class LoginController {
	@Autowired
	private SecurityUserDetailService securityUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public ModelAndView register() {
		return new ModelAndView("registery");
	}

	@PostMapping("/register")
	public String createUser(Model model, @RequestParam String username, @RequestParam String password,
			@RequestParam String role) {
		String encryptedPassword = passwordEncoder.encode(password);
		User user = new User(username, encryptedPassword, role);
		securityUserDetailsService.createUser(user);
		return "User Created Successfully";
	}
	
	@GetMapping("/")
	public String homePage() {
		return "Welcome to Home Page";
	}
	
	
	@GetMapping("/dev")
	public String developer() {
		return "Welcome Developer";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Welcome ADMIN";
	}
}
