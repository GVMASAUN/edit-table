package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public interface EmployeeService {
	public void createNewEmployee();

	public void updateEmployee();
	
	public void updateEmployeeAPI(Employee employee);
	
	public void addNewEmployeeByDeptIdAPI(Employee employee);
	
	public void saveEmpmloyee(Employee employee);

	public Employee displayEmployeeData();
	
	public Employee getEmployeeById(Long employeeId);

	public void addNewEmployeeByDeptId();

	public void deleteEmployee();
	
	public void deleteEmployeeAPI(Long employeeId);
}
