package vn.hoapm.springboot.domain.account.service.usecase;


import vn.hoapm.springboot.domain.account.presentaion.UserRequest;

/**
 * @ClassName :IUserDeleteUC
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/17/2021 7:10 PM
 */
public interface IUserDeleteUC {
    IUserDeleteUC applyData(UserRequest userRequest);

    IUserDeleteUC checkExistedUser(Long id);

    IUserDeleteUC deleteUser();

    IUserDeleteUC fail();

    String getResult();
}
