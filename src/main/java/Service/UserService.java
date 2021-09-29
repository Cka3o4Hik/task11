package Service;

import Entity.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserService {
	private SessionFactory factory;

	public UserService(SessionFactory factory) {
		this.factory = factory;
	}

	public Integer add(UsersEntity user) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userID = null;

		try {
			tx = session.beginTransaction();
			userID = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userID;
	}

	public List<UsersEntity> getAll() {
		Session session = factory.openSession();
		Transaction tx = null;
		List users = null;
		try {
			tx = session.beginTransaction();
			users = session.createQuery("FROM UsersEntity").list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	public void update(UsersEntity user) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteById(Integer userID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			UsersEntity user = session.get(UsersEntity.class, userID);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public UsersEntity getById(Integer userID) {
		Session session = factory.openSession();
		Transaction tx = null;
		UsersEntity user = null;
		try {
			tx = session.beginTransaction();
			user = session.get(UsersEntity.class, userID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
}
