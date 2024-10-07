package com.codeinsight.exercise.hibernateRelationships.DAO;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.codeinsight.exercise.hibernateRelationships.model.Employee;

public class EmployeeDAO {
	private SessionFactory sessionFactory;

	public EmployeeDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(employee);
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
	}

	public void updateEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(employee);
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
	}

	public Employee getEmployeeById(Long employeeId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Employee employee = null;
		try {
			transaction = session.beginTransaction();
			employee = session.get(Employee.class, employeeId);
			Hibernate.initialize(employee.getDepartment());
			Hibernate.initialize(employee.getHobbies());
			Hibernate.initialize(employee.getProjects());
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return employee;
	}

	public void deleteEmployee(Long employeeId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			if (employee != null) {
				employee.setProjects(null);
				employee.setDepartment(null);
				session.remove(employee);
				transaction.commit();
				System.out.println("Employee deleted successfully!!!");
			} else {
				System.out.println("Employee with this ID not Exists");
			}
			
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
	}
}
