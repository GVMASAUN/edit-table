package com.codeinsight.exercise.hibernateRelationships.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Address {
	private Long id;
	private String addressName;
	@JsonIgnore
	private Employee employee;

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String address) {
		this.addressName = address;
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

	@Override
	public int hashCode() {
		if (id == null) {
			return addressName.hashCode();
		}
		return addressName.hashCode() ^ id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return id.equals(((Address) obj).getId()) && addressName.equals(((Address) obj).getAddressName());
	}
}
