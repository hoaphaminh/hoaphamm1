package vn.hoapm.springbootV2.dao.impl;

import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dao.HibernateGenericDAO;
import vn.hoapm.springbootV2.dao.UserInfoDao;
import vn.hoapm.springbootV2.entities.UserInfo;

import java.util.List;

public class HibernatePartnerDao extends HibernateGenericDAO<UserInfo, Long> implements UserInfoDao {

    @Override
    public Class<?> getDomain() {
        return UserInfo.class;
    }


}
