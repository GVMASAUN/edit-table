package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public interface EntityCRUDService {
	public Long addNewEmployeeByDeptId(Employee employee) throws Exception;
	
	public Employee getEmployeeById(Long employeeId) throws Exception;
	
	public void saveEmployee(Employee employee) throws Exception;
	
	public void deleteEmployee(Long employeeId) throws Exception;
	
	public void updateEmployee(Employee employee) throws Exception;
}
