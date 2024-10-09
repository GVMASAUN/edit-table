package com.codeinsight.exercise.hibernateRelationships.model;

import java.util.List;
import java.util.Set;

public class Employee {
	private Long id;
	private String name;

	private Address address;
	private Department department;
	private List<Hobby> hobbies;
	private Set<Project> projects;
	private Long departmentId;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Employee() {
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public void addProject(Project project) {
		this.projects.add(project);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address.getAddressName() + ", department="
				+ department.getName() + ", hobbies=" + hobbies.get(0).getName() + " deptId: " + departmentId + "]";
	}
}
