package com.codeinsight.exercise.hibernateRelationships;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codeinsight.exercise.hibernateRelationships.service.DepartmentService;
import com.codeinsight.exercise.hibernateRelationships.service.EmployeeService;

public class App {

	public static void displayEmployeeData(ApplicationContext appContext) {
		EmployeeService empService = (EmployeeService) appContext.getBean("employeeService");
		empService.displayEmployeeData();
	}

	public static void displayDepartmentData(ApplicationContext appContext) {
		DepartmentService deptDAO = (DepartmentService) appContext.getBean("departmentService");
		deptDAO.displayDepartmentData();
	}

	public static void addNewDepartment(ApplicationContext appContext) {
		DepartmentService deptService = (DepartmentService) appContext.getBean("departmentService");
		deptService.addNewDepartment();
	}

	public static void updateEmployee(ApplicationContext appContext) {
		EmployeeService empService = (EmployeeService) appContext.getBean("employeeService");
		empService.updateEmployee();
	}

	public static void deleteEmployee(ApplicationContext appContext) {
		EmployeeService empService = (EmployeeService) appContext.getBean("employeeService");
		empService.deleteEmployee();
	}
	
	public static void addNewEmployeeByDeptId(ApplicationContext appContext) {
		EmployeeService empService = (EmployeeService) appContext.getBean("employeeService");
		empService.addNewEmployeeByDeptId();
	}

	public static void createNewEmployee(ApplicationContext appContext) {
		EmployeeService empService = (EmployeeService) appContext.getBean("employeeService");
		empService.createNewEmployee();
	}

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");
		try {
//			createNewEmployee(appContext);
//			addNewEmployeeByDeptId(appContext);
//			displayEmployeeData(appContext);
//			addNewDepartment(appContext);
//			updateEmployee(appContext);
//			displayDepartmentData(appContext);
			deleteEmployee(appContext);
		} finally {
			((AbstractApplicationContext) appContext).close();
		}

	}
}
