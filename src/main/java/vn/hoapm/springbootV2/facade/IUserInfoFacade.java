package vn.hoapm.springbootV2.facade;

import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dto.UserInfoDTO;

import java.util.List;

public interface IUserInfoFacade {
    List<UserInfoDTO> getUserInforDTOs(int offset, int limit) throws CommonException;

    UserInfoDTO getUserInfoDto(long userInfoId) throws CommonException;
}
