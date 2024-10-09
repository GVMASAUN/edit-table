package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public interface EmployeeService {
	public void createNewEmployee() throws Exception;

	public void updateEmployee();
			
	public void saveEmployee(Employee employee) throws Exception;

	public Employee displayEmployeeData();
	
	public Employee getEmployeeById(Long employeeId) throws Exception;

	public void addNewEmployeeByDeptId();

	public void deleteEmployee() throws Exception;
}
