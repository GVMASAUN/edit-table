package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Department;

public interface DepartmentService {
	public void displayDepartmentData();

	public Department getDepartmentById(Long deptId);

	public void addNewDepartment();
}
