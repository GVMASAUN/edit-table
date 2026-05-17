package com.codeinsight.exercise.user;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String email;
	private String password;

	public Product() {
	}

	public Product(String name, String email, String password) {
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

	@Override
	public String toString() {
		return "User:{" + "Name: " + getName() + ", Email: " + getEmail() + "}";
	}
}
