package com.codeinsight.exercise.hibernateRelationships.service;

import java.util.List;
import java.util.Scanner;

import com.codeinsight.exercise.hibernateRelationships.DAO.DepartmentDAO;
import com.codeinsight.exercise.hibernateRelationships.model.Department;
import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDAO departmentDAO;

	public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	@Override
	public void displayDepartmentData() {
		Long departmentId;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Department Id: ");
		departmentId = scanner.nextLong();
		Department department = departmentDAO.getDepartmentById(departmentId);

		if (department != null) {
			List<Employee> employees = department.getEmployees();
			System.out.print("Department Name: " + department.getName() + "\nEmployees id: ");
			employees.forEach(emp -> System.out.print(emp.getName() + ", "));
		} else {
			System.out.println("Department with this id not exists!!!");
		}
		scanner.close();
	}

	@Override
	public void addNewDepartment() {
		String departmentName;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the new Department name: ");
		departmentName = scanner.nextLine();
		Department department = new Department(departmentName);
		scanner.close();

		departmentDAO.saveDepartment(department);
	}

	@Override
	public Department getDepartmentById(Long deptId) {
		return departmentDAO.getDepartmentById(deptId);
	}

}
