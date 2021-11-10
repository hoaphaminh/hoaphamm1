package vn.hoapm.springbootV2.service;

import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.entities.UserInfo;

import java.util.List;

public interface IUserInfoService {
    List<UserInfo> getUserInfos(int offset, int limit) throws CommonException;

    UserInfo getUserInfo(long userInfoId) throws CommonException;
}
