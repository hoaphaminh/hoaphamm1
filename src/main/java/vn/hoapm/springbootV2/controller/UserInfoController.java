package vn.hoapm.springbootV2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springbootV2.dto.UserInfoDTO;
import vn.hoapm.springbootV2.facade.IUserInfoFacade;
import vn.hoapm.springbootV2.util.CommonConstant;

import java.util.List;

@RestController
@RequestMapping("/v2/user_info")
public class UserInfoController {

    private final IUserInfoFacade userInfoFacade;

    @Autowired
    public UserInfoController(IUserInfoFacade userInfoFacade) {
        this.userInfoFacade = userInfoFacade;
    }

    @GetMapping()
    public ResponseEntity<List<UserInfoDTO>> getUserInfos(
            @RequestParam(value = "offset", defaultValue = CommonConstant.DEFAULT_OFFSET) int offset,
            @RequestParam(value = "limit", defaultValue = CommonConstant.DEFAULT_LIMIT) int limit
    ) throws CommonException {
        List<UserInfoDTO> userInfoDTOs = userInfoFacade.getUserInforDTOs(offset, limit);
        return new ResponseEntity<>(userInfoDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{userinfo_id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable("userinfo_id") long userInfoId ) throws CommonException {
        UserInfoDTO userInfoDTO = userInfoFacade.getUserInfoDto(userInfoId);
        return new ResponseEntity<>(userInfoDTO,HttpStatus.OK);
    }
}
