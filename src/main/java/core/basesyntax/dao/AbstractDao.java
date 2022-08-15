package core.basesyntax.dao;

import core.basesyntax.exception.DataProcessingException;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;
    protected final Class<T> clazz;

    protected AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public T save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot create " + clazz.getSimpleName() + ": "
                    + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get " + clazz.getSimpleName().toLowerCase()
                    + " from DB by id: " + id, e);
        }
    }

    public List<T> getAll() {
        String hql = "FROM " + clazz.getSimpleName();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql, clazz).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Cannot get all " + clazz.getSimpleName().toLowerCase() + "s from DB", e);
        }
    }

    public T update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot update " + clazz.getSimpleName() + ": "
                    + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(clazz, id));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Cannot delete " + clazz.getSimpleName() + " by id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
