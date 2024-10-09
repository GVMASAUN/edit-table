package com.codeinsight.exercise.hibernateRelationships.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonManipulationService {
	public Employee getEmployeeFromJson(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder jsonString = new StringBuilder();

		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			jsonString.append(line);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Employee employee = objectMapper.readValue(jsonString.toString(), Employee.class);
		
		return employee;
	}
}
