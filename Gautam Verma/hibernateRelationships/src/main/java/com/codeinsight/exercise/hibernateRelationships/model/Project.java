package com.codeinsight.exercise.hibernateRelationships.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Project {
	private Long projectId;
	private String name;
	@JsonIgnore
	private Set<Employee> employees;

	public Project() {
	}

	public Project(String projectName) {
		this.name = projectName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		if (projectId == null) {
			return name.hashCode();
		}
		return name.hashCode() ^ projectId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return projectId.equals(((Project) obj).getProjectId()) && name.equals(((Project) obj).getName());
	}
}
