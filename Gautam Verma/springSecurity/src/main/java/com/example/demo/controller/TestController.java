package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public String test() {
		return "Hello from Application which is going to be Spring Secure";
	}
	
	@GetMapping("/courses")
	public String courses() {
		return "Available courses:" +
				"<ul>" +
				"<li>Learn JAVA : Beginner to Master</li>" +
				"<li>Full Stack JAVA Developer</li>" +
				"<li>Microservices with Spring Boot</li>" +
				"<li>Complete Web Development</li>" +
				"<li>Wordpress for Beginner</li>" +
				"<li>Complete Python Development</li>" +
				"<li>Docker guide : Beginner to Master</li>" +
				"<li>Node.js : Ultimate guide</li>" +
				"</ul>";
	}
}
