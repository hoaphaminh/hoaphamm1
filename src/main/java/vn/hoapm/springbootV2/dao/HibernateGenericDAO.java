package vn.hoapm.springbootV2.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import vn.hoapm.springboot.application.exception.CommonException;

import java.io.Serializable;
import java.util.List;

/**
 * HibernateGenericDao expands provides a set of basic CRUD functionality common to almost all potential DAOs. It is built with Generics, and requires
 * as parameters the Type, and the Type of the primary key for locating the Type in the persistence layer.
 * * @param <D>
 * *            The class of the Domain object to be handled by the Concrete DAO implementation
 * * @param <K>
 * *            The type of the primary key of the Domain object
 **/
public abstract class HibernateGenericDAO<D, K extends Serializable> implements GenericDao<D, K> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HibernateGenericDAO.class);
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Session getCurrentSession() {
        SessionFactory sf = sessionFactory;
        return sf.getCurrentSession();
    }

    public abstract Class<?> getDomain();

    @Override
    public List<D> getAll() throws CommonException {
        try {
            return this.getCurrentSession()
                    .createQuery("FROM " + getDomain().getSimpleName())
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception while getting all {}", getDomain().getSimpleName(), e);
            throw new CommonException("Exception occurs in getAll object", e);
        }
    }

    @Override
    public List<D> getAll(int offset, int limit) throws CommonException {
        try {
            return this.getCurrentSession()
                    .createQuery("FROM " + getDomain().getSimpleName())
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error("Exception while getting all {}", getDomain().getSimpleName(), e);
            throw new CommonException("Exception occurs in getAll object", e);
        }
    }


    @Override
    public D getById(K id) throws CommonException {
        return getById(id, getDomain());
    }

    private D getById(K id, Class<?> c) throws CommonException {
        if (id == null) {
            return null;
        }
        try {
            D obj = (D) getCurrentSession().get(c, id);
            return obj;
        } catch (Exception e) {
            LOGGER.debug("Exception occurs on getById", id, e);
            throw new CommonException("Exception occurs on getById");
        }
    }

}
