package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public interface EmployeeService {
	public void createNewEmployee();

	public void updateEmployee();

	public Employee displayEmployeeData();

	public void addNewEmployeeByDeptId();

	public void deleteEmployee();
}
