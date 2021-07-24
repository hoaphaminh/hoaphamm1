package vn.hoapm.springboot.domain.account.service.impl;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.presentaion.UserRequest;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.account.service.usecase.IUserDeleteUC;

/**
 * @ClassName :UserDeleteUCImpl
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/17/2021 7:16 PM
 */
public class UserDeleteUCImpl implements IUserDeleteUC {
    private UserRepository userRepository;
    private UserCUD userCUD;
    private int countExistedRecord;
    private int executedRecord;

    public UserDeleteUCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public IUserDeleteUC applyData(UserRequest userRequest) {
        this.userCUD = userRequest.getCud();
        return this;
    }

    @Override
    public IUserDeleteUC checkExistedUser(Long id) {
        this.countExistedRecord = userRepository.countExistedEnabledRecord(id);
        if (countExistedRecord == 0) {
            // TODO throw exception
        }
        return this;
    }

    @Override
    public IUserDeleteUC deleteUser() {
        this.executedRecord = userRepository.deleteUser(userCUD);
        return this;
    }

    @Override
    public IUserDeleteUC fail() {
        if (executedRecord == 0) {
            //delete fail
        }
        return this;
    }

    @Override
    public String getResult() {
        return "user.delete.success";
    }
}
