package Service;

import Entity.CommentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CommentService {
	private SessionFactory factory;

	public CommentService(SessionFactory factory) {
		this.factory = factory;
	}

	public Integer add(CommentEntity comments) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer commentsID = null;

		try {
			tx = session.beginTransaction();
			commentsID = (Integer) session.save(comments);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commentsID;
	}

	public List<CommentEntity> getAll() {
		Session session = factory.openSession();
		Transaction tx = null;
		List advertisements = null;
		try {
			tx = session.beginTransaction();
			advertisements = session.createQuery("FROM CommentEntity").list();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return advertisements;
	}

	public void update(CommentEntity comments) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(comments);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void deleteById(Integer commentsID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CommentEntity comment = session.get(CommentEntity.class, commentsID);
			session.delete(comment);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public CommentEntity getById(Integer commentsID) {
		Session session = factory.openSession();
		Transaction tx = null;
		CommentEntity comments = null;
		try {
			tx = session.beginTransaction();
			comments = session.get(CommentEntity.class, commentsID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return comments;
	}
}
