package vn.hoapm.springbootV2.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dto.UserInfoDTO;
import vn.hoapm.springbootV2.entities.UserInfo;
import vn.hoapm.springbootV2.facade.IUserInfoFacade;
import vn.hoapm.springbootV2.mapper.UserInfoEntityMapper;
import vn.hoapm.springbootV2.service.IUserInfoService;

import java.util.List;

@Service
public class UserInfoFacade implements IUserInfoFacade {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoEntityMapper userInfoMapper;

    @Override
    public List<UserInfoDTO> getUserInforDTOs(int offset, int limit) throws CommonException {
        List<UserInfo> userInfos = userInfoService.getUserInfos(offset, limit);
        return userInfoMapper.mapEntitesToListDTO(userInfos);
    }
}
