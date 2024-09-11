package com.codeinsight.exercise.loginPage;

import jakarta.validation.constraints.*;

public class UserForm {
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotEmpty(message="email is required")
	private String email;
	
	@NotEmpty(message="password is required")
	private String password;
	
	public UserForm() {
	}

	public UserForm(String name, String email, String password) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// getters
	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}
}
