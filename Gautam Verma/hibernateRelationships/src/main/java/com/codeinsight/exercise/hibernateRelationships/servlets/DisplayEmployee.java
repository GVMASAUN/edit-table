package com.codeinsight.exercise.hibernateRelationships.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;
import com.codeinsight.exercise.hibernateRelationships.service.EntityCRUDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DisplayEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayEmployee() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		if (appContext != null) {
			EntityCRUDService empService = (EntityCRUDService) appContext.getBean("entityCrudService");
			String pathInfo[] = request.getPathInfo().split("/");
			String pathEmployeeId = pathInfo[pathInfo.length - 1];
			Long employeeId = null;
			Employee employee = null;
			
			try {
				employeeId = Long.parseLong(pathEmployeeId);
				employee = empService.getEmployeeById(employeeId);
			} catch (Exception exception) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
				return;
			}

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			try {
				String jsonString = mapper.writeValueAsString(employee);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(jsonString);
				out.flush();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}
}
