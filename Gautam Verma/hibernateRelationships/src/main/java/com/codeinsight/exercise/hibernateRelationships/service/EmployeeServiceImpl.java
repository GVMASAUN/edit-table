package com.codeinsight.exercise.hibernateRelationships.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.codeinsight.exercise.hibernateRelationships.DAO.DepartmentDAO;
import com.codeinsight.exercise.hibernateRelationships.DAO.EmployeeDAO;
import com.codeinsight.exercise.hibernateRelationships.model.Address;
import com.codeinsight.exercise.hibernateRelationships.model.Department;
import com.codeinsight.exercise.hibernateRelationships.model.Employee;
import com.codeinsight.exercise.hibernateRelationships.model.Hobby;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;
	private DepartmentDAO departmentDAO;

	public EmployeeServiceImpl(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO) {
		this.employeeDAO = employeeDAO;
		this.departmentDAO = departmentDAO;
	}

	private void createNewEmployee(Employee employee) {
		employeeDAO.createEmployee(employee);
	}

	@Override
	public void createNewEmployee() {
		Scanner scanner = new Scanner(System.in);
		String name;
		String departmentName;
		String employeeAddress;
		String employeeHobby;

		System.out.println("Enter the details for new Employee");
		System.out.print("Name: ");
		name = scanner.nextLine();

		System.out.print("New Department Name: ");
		departmentName = scanner.nextLine();

		System.out.print("Address: ");
		employeeAddress = scanner.nextLine();

		System.out.print("Hobby: ");
		employeeHobby = scanner.nextLine();

		Employee employee = new Employee();
		employee.setName(name);

		Department department = new Department();
		department.setName(departmentName);

		department.getEmployees().add(employee);
		employee.setDepartment(department);

		Address address = new Address();
		address.setAddress(employeeAddress);
		employee.setAddress(address);

		Hobby hobby = new Hobby();
		hobby.setName(employeeHobby);
		List<Hobby> hobbies = new ArrayList<>();
		hobby.setEmployee(employee);
		hobbies.add(hobby);
		employee.setHobbies(hobbies);

		scanner.close();

		createNewEmployee(employee);
	}

	@Override
	public void displayEmployeeData() {
		Long employeeId;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Employee Id: ");
		employeeId = scanner.nextLong();
		Employee employee = employeeDAO.getEmployeeById(employeeId);

		if (employee != null) {
			List<Hobby> hobbies = employee.getHobbies();
			System.out.print("Employee Name: " + employee.getName() + "\nAddress: " + employee.getAddress().getAddress()
					+ "\nDepartment: " + employee.getDepartment().getName() + "\nHobbies: ");
			hobbies.forEach(hobby -> System.out.print(hobby.getName() + ", "));
		} else {
			System.out.println("Employee with this id not exists!!!");
		}
		scanner.close();
	}

	private void addNewEmployeeByDeptId(Employee employee) {
		employeeDAO.updateEmployee(employee);
	}

	public void deleteEmployee() {
		Long employeeId;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Employee id to be deleted: ");
		employeeId = scanner.nextLong();
		scanner.close();

		employeeDAO.deleteEmployee(employeeId);
	}

	private void updateEmployee(Long employeeId) {
		Scanner scanner = new Scanner(System.in);
		Employee employee = employeeDAO.getEmployeeById(employeeId);
		System.out.print("Want to change Address: (y/n) ");
		String choice = scanner.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the changed address: ");
			choice = scanner.nextLine();

			Address address = employee.getAddress();
			address.setAddress(choice);
		}

		System.out.print("Want to change department: (y/n) ");

		choice = scanner.nextLine();
		Long deptId;

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the changed department id: ");
			deptId = scanner.nextLong();
			Department dept = departmentDAO.getDepartmentById(deptId);

			if (dept != null) {
				employee.setDepartment(dept);
			}
		}

		System.out.print("Want to add Hobby: (y/n) ");
		choice = scanner.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the new hobby: ");
			choice = scanner.nextLine();

			Hobby newHobby = new Hobby(choice);
			newHobby.setEmployee(employee);
			employee.getHobbies().add(newHobby);
		}
		scanner.close();
		employeeDAO.updateEmployee(employee);
	}

	@Override
	public void addNewEmployeeByDeptId() {
		Scanner scanner = new Scanner(System.in);

		String employeeName;
		String employeeAddress;
		String employeeHobby;
		Long departmentId;

		System.out.println("Enter Employee Details and existing department id: ");
		System.out.print("Employee name: ");
		employeeName = scanner.nextLine();
		Employee employee = new Employee();
		employee.setName(employeeName);

		System.out.print("Address: ");
		employeeAddress = scanner.nextLine();
		Address address = new Address();
		address.setAddress(employeeAddress);
		employee.setAddress(address);

		System.out.print("Hobby: ");
		employeeHobby = scanner.nextLine();
		Hobby hobby = new Hobby();
		hobby.setName(employeeHobby);
		List<Hobby> hobbies = new ArrayList<>();
		hobby.setEmployee(employee);
		hobbies.add(hobby);
		employee.setHobbies(hobbies);

		System.out.println("Enter existing department id: ");
		departmentId = scanner.nextLong();

		Department department = departmentDAO.getDepartmentById(departmentId);
		employee.setDepartment(department);

		scanner.close();
		addNewEmployeeByDeptId(employee);
	}

	@Override
	public void updateEmployee() {
		Long employeeId;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Employee id to be updated: ");
		employeeId = scanner.nextLong();
		scanner.close();

		updateEmployee(employeeId);

	}
}
