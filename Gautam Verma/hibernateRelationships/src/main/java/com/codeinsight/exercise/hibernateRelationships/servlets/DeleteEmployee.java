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

import com.codeinsight.exercise.hibernateRelationships.service.EntityCRUDService;

@WebServlet("/employee/delete/*")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteEmployee() {
		super();
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		if (appContext != null) {
			EntityCRUDService apiEndpointService = (EntityCRUDService) appContext.getBean("entityCrudService");
			String pathInfo[] = request.getPathInfo().split("/");
			String pathEmployeeId = pathInfo[pathInfo.length - 1];
			Long employeeId = null;

			try {
				employeeId = Long.parseLong(pathEmployeeId);
				apiEndpointService.deleteEmployee(employeeId);
			} catch (Exception exception) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
				return;
			}
		}
		
		response.getWriter().append("User Deleted Successfully");
	}
}
