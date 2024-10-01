package com.codeinsight.exercise.hibernateRelationships.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.codeinsight.exercise.hibernateRelationships.DAO.ProjectDAO;
import com.codeinsight.exercise.hibernateRelationships.DAO.DepartmentDAO;
import com.codeinsight.exercise.hibernateRelationships.DAO.EmployeeDAO;
import com.codeinsight.exercise.hibernateRelationships.model.Address;
import com.codeinsight.exercise.hibernateRelationships.model.Project;
import com.codeinsight.exercise.hibernateRelationships.model.Department;
import com.codeinsight.exercise.hibernateRelationships.model.Employee;
import com.codeinsight.exercise.hibernateRelationships.model.Hobby;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;
	private DepartmentDAO departmentDAO;
	private ProjectDAO projectDAO;

	public EmployeeServiceImpl(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, ProjectDAO projectDAO) {
		this.employeeDAO = employeeDAO;
		this.departmentDAO = departmentDAO;
		this.projectDAO = projectDAO;
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
		String projectName;

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

		System.out.println("Enter the project details: ");
		Set<Project> projects = new HashSet<Project>();
		Project project;
		for (int i = 0; i < 2; ++i) {
			System.out.print("Enter Project Name: ");
			projectName = scanner.nextLine();
			project = new Project(projectName);
			projects.add(project);
		}
		employee.setProjects(projects);

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
			System.out.print("\nProjects: ");
			Set<Project> projects = employee.getProjects();
			projects.forEach(project -> System.out.print(project.getName() + " , "));
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
			} else {
				System.out.println("Enter department does not exist!!! ");
			}
		}
		scanner.nextLine();

		System.out.print("Want to add Hobby: (y/n) ");
		choice = scanner.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the new hobby: ");
			choice = scanner.nextLine();

			Hobby newHobby = new Hobby(choice);
			newHobby.setEmployee(employee);
			employee.getHobbies().add(newHobby);
		}

		System.out.print("Want to enroll in other project? (y/n): ");
		choice = scanner.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the Project id: ");
			Long projectId = scanner.nextLong();
			scanner.nextLine();
			Project project = projectDAO.getProjectById(projectId);

			employee.addProject(project);
		}
		scanner.nextLine();
		System.out.print("Want to get out from any project? (y/n): ");
		choice = scanner.nextLine();

		if (choice.equals("y") || choice.equals("Y")) {
			System.out.print("Enter the Project id: ");
			Long projectId = scanner.nextLong();
			Project project = projectDAO.getProjectById(projectId);

			employee.getProjects().remove(project);
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
		Long projectId;

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

		System.out.print("Enter project Id: ");
		projectId = scanner.nextLong();

		Project project = projectDAO.getProjectById(projectId);
		Set<Project> projects = new HashSet<Project>();
		projects.add(project);
		employee.setProjects(projects);

		scanner.close();
		addNewEmployeeByDeptId(employee);
	}

	@Override
	public void updateEmployee() {
		Long employeeId;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Employee id to be updated: ");
		employeeId = scanner.nextLong();
		updateEmployee(employeeId);

	}
}
