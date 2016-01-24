package net.hamtag.server.core;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RootMgr {
	private static SessionFactory sessionFactory;
	private static Session session;
	private static SessionFactory getInstancedSessionFactory() {
		try {
			if (sessionFactory != null)
				return sessionFactory;
			sessionFactory = new Configuration().configure("/net/hamtag/server/core/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		return sessionFactory;
	}

	public static Session getInstance(){
		if(session!=null&&session.isOpen())
			return session;
		session=getInstancedSessionFactory().openSession();
		return session;
	}

	public static Long add(Object object) {
		Session session = getInstance();
		Transaction tx = null;
		Long addedId = null;
		try {
			tx = session.beginTransaction();
			addedId = (Long) session.save(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return addedId;
	}

	public static void update(Object toUpdate) {
		Session session = getInstance();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(toUpdate);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	@Deprecated
	public static void delete(Long id, Class<Object> classType) {
		Session session = getInstance();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Object toDelete = session.get(classType, id);
			session.delete(toDelete);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
