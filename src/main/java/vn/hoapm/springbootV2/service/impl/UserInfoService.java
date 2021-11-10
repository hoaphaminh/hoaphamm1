package vn.hoapm.springbootV2.service.impl;

import org.springframework.stereotype.Component;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.entities.UserInfo;
import vn.hoapm.springbootV2.service.IUserInfoService;

import java.util.List;

@Component
public class UserInfoService extends AbstractBasedService implements IUserInfoService {
    @Override
    public List<UserInfo> getUserInfos(int offset, int limit) throws CommonException {
        return daoFactory.getUserInfoDao().getAll(offset, limit);
    }

    @Override
    public UserInfo getUserInfo(long userInfoId) throws CommonException {
        return daoFactory.getUserInfoDao().getById(userInfoId);
    }
}
