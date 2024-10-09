package com.codeinsight.exercise.hibernateRelationships.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;
import com.codeinsight.exercise.hibernateRelationships.service.EntityCRUDService;
import com.codeinsight.exercise.hibernateRelationships.service.JsonManipulationService;

@WebServlet("/add-in-existing")
public class ExistingDepartmentEmployeeAdder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExistingDepartmentEmployeeAdder() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (appContext != null) {
			EntityCRUDService apiEndpointService = (EntityCRUDService) appContext.getBean("entityCrudService");

			JsonManipulationService jsonManipulationService = new JsonManipulationService();

			Employee employee = jsonManipulationService.getEmployeeFromJson(request.getInputStream());
			Long employeeId = null;
			try {
				employeeId = apiEndpointService.addNewEmployeeByDeptId(employee);
			} catch (Exception exception) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Exception: " + exception.getMessage());
				return;
			}

			response.sendRedirect(request.getContextPath() + "/employee/" + employeeId);
		}
	}

}
