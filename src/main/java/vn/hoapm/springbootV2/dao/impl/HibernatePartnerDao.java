package vn.hoapm.springbootV2.dao.impl;

import org.springframework.stereotype.Repository;
import vn.hoapm.springbootV2.dao.HibernateGenericDAO;
import vn.hoapm.springbootV2.dao.UserInfoDao;
import vn.hoapm.springbootV2.entities.UserInfo;

@Repository
public class HibernatePartnerDao extends HibernateGenericDAO<UserInfo, Long> implements UserInfoDao {

    @Override
    public Class<?> getDomain() {
        return UserInfo.class;
    }

}
