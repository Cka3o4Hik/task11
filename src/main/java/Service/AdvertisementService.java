package Service;


import Entity.AdvertisementEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AdvertisementService {
	private SessionFactory factory;

	public AdvertisementService(SessionFactory factory) {
		this.factory = factory;
	}

	public Integer add(AdvertisementEntity advertisement) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userID = null;

		try {
			tx = session.beginTransaction();
			userID = (Integer) session.save(advertisement);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userID;
	}

	public List<AdvertisementEntity> getAll() {
		Session session = factory.openSession();
		Transaction tx = null;
		List advertisements = null;
		try {
			tx = session.beginTransaction();
			advertisements = session.createQuery("FROM AdvertisementEntity").list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return advertisements;
	}

	public void update(AdvertisementEntity comment) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(comment);
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
			AdvertisementEntity user = session.get(AdvertisementEntity.class, userID);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public AdvertisementEntity getById(Integer userID) {
		Session session = factory.openSession();
		Transaction tx = null;
		AdvertisementEntity user = null;
		try {
			tx = session.beginTransaction();
			user = session.get(AdvertisementEntity.class, userID);
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
