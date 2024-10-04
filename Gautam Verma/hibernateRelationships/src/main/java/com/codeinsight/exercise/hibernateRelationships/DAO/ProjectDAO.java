package com.codeinsight.exercise.hibernateRelationships.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.codeinsight.exercise.hibernateRelationships.model.Project;

public class ProjectDAO {
	private SessionFactory sessionFactory;

	public ProjectDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Project getProjectById(Long projectId) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		Project project = null;
		try {
			transaction = session.beginTransaction();
			project = session.get(Project.class, projectId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return project;
	}
}
