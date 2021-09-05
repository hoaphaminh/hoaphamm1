package vn.hoapm.springbootV2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
    HibernateDaoFactory provides automatic Injection of all DAO beans that use the Hibernate persistence layer.
    DaoFactory is used to build a DAO which requires in the system.
    Key roles is pass a valid HibernateSession factory to each new DAO to use.
    In the application context, session is injected into each DAO object.
 */
@Repository(value = "daoFactory")
public class HibernateDaoFactory implements DaoFactory {

    public HibernateDaoFactory() {
    }

    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }
}
