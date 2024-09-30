package com.codeinsight.exercise.hibernateRelationships.model;

public class Hobby {
	private Long id;
	private String name;

	private Employee employee;

	public Hobby(String hobby) {
		this.name = hobby;
	}

	public Hobby() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
