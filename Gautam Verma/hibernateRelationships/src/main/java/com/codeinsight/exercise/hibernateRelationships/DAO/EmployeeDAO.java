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
			throw exception;
		}
	}

	public Long updateEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Employee mergedEmployee = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			mergedEmployee = session.merge(employee);
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
		return mergedEmployee.getId();
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

	public void deleteEmployee(Long employeeId) throws Exception{
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
				throw new Exception("Employee with this Id not exists!!!");
			}
			
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
			throw new Exception("Employee with this Id not exists");
		}
	}
}
