package com.codeinsight.exercise.hibernateRelationships.service;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public class EntityCRUDServiceImpl implements EntityCRUDService {
	private EmployeeServiceImpl employeeServiceImpl;

	public EntityCRUDServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	@Override
	public Long addNewEmployeeByDeptId(Employee employee) throws Exception {
		return employeeServiceImpl.addEmployeeByDeptId(employee);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) throws Exception {
		return employeeServiceImpl.getEmployeeById(employeeId);
	}

	@Override
	public void deleteEmployee(Long employeeId) throws Exception {
		employeeServiceImpl.deleteEmployee(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee) throws Exception {
		employeeServiceImpl.updateEmployee(employee);
	}

	@Override
	public void saveEmployee(Employee employee) throws Exception {
		employee.getHobbies().forEach(hobby -> {
			hobby.setEmployee(employee);
		});
		employeeServiceImpl.saveEmployee(employee);
	}
}
