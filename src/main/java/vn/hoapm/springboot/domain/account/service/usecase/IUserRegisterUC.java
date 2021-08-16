package vn.hoapm.springboot.domain.account.service.usecase;


import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;

public interface IUserRegisterUC {
    IUserRegisterUC applyRequest(UserCUD userCUD);

    IUserRegisterUC validateExisting() throws CommonException;

    IUserRegisterUC encodePassword();

    IUserRegisterUC createPersonalAccount();

    IUserRegisterUC checkInsertSuccess() throws CommonException, CommonException;

    IUserRegisterUC createRoles();

    UserResponse end();
}
