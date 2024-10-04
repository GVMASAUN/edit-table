package com.codeinsight.exercise.hibernateRelationships.service;

import java.util.Scanner;

import com.codeinsight.exercise.hibernateRelationships.DAO.ProjectDAO;
import com.codeinsight.exercise.hibernateRelationships.model.Project;

public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;

	public ProjectServiceImpl(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Override
	public void displayProjectDetails() {
		Long projectId;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter project id: ");
		projectId = scanner.nextLong();

		scanner.close();
		Project project = projectDAO.getProjectById(projectId);
		System.out.print("Project Name: " + project.getName() + "\nEmployees: ");
		project.getEmployees().forEach(employee -> System.out.print(employee.getId() + " , "));

	}

}
