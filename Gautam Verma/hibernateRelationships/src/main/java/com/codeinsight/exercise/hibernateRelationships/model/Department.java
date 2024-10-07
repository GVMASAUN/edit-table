package com.codeinsight.exercise.hibernateRelationships.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Department {
	private Long id;
	private String name;

	@JsonIgnore
	private List<Employee> employees = new ArrayList<>();

	public Department() {
	}

	public Department(String departmentName) {
		this.name = departmentName;
		this.employees = null;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public int hashCode() {
		if (id == null) {
			return name.hashCode();
		}
		return name.hashCode() ^ id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return id.equals(((Department) obj).getId()) && name.equals(((Department) obj).getName());
	}
}
