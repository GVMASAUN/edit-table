package com.codeinsight.exercise.hibernateRelationships.DAO;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.codeinsight.exercise.hibernateRelationships.model.Department;

public class DepartmentDAO {
	private SessionFactory sessionFactory;

	public DepartmentDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Department getDepartmentById(Long departmentId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Department department = null;
		try {
			transaction = session.beginTransaction();
			department = session.get(Department.class, departmentId);
			Hibernate.initialize(department.getEmployees());
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return department;
	}

	public void saveDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(department);
			transaction.commit();
		} catch (Exception exception) {
			if (transaction != null) {
				transaction.rollback();
			}
			exception.printStackTrace();
		}
	}
}
