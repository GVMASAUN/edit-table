package com.codeinsight.exercise.hibernateRelationships.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Address {
	private Long id;
	private String address;
	@JsonIgnore
	private Employee employee;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
