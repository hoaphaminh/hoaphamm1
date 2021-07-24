package vn.hoapm.springboot.domain.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.account.factory.User;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.account.service.usecase.IUserRegisterUC;

public class UserRegisterUCImpl implements IUserRegisterUC {
    private final UserRepository repository;
    private UserCUD userCUD;
    private long idReturn;
    private UserResponse userResponse;


    @Autowired
    PasswordEncoder passwordEncoder;


    public UserRegisterUCImpl(UserRepository repository) {
        this.repository = repository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public IUserRegisterUC applyRequest(UserCUD userCUD) {
        this.userCUD = userCUD;
        return this;
    }

    @Override
    public IUserRegisterUC validate() {
        return this;
    }

    @Override
    public IUserRegisterUC encodePassword() {
        userCUD.setPassword(passwordEncoder.encode(userCUD.getPassword()));
        return this;
    }


    @Override
    public IUserRegisterUC createPersonalAccount() {
        idReturn = repository.create(userCUD);
        return this;
    }

    @Override
    public IUserRegisterUC createRoles() {
        if (idReturn > 0){
            User user = User.builder()
                .roleCode(userCUD.getRoleCode())
                .id(idReturn)
                .build();
            int executedRecord = repository.createRole(user);

        }
        return this;
    }

    @Override
    public IUserRegisterUC checkInsertSuccess() throws CommonException {
        if (idReturn == 0)
        {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Insert failured!!!");
        } else {
            UserSearch search = UserSearch.builder()
                    .username(userCUD.getUsername())
                    .build();
            userResponse = repository.findBindUsername(search);
        }
        return this;
    }


    @Override
    public UserResponse end() {
        return userResponse;
    }
}
