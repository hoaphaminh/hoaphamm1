package vn.hoapm.springboot.domain.account.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.application.rest.user.UserJSONResponse;
import vn.hoapm.springboot.domain.account.presentaion.UserRequest;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.account.service.impl.UserDeleteUCImpl;
import vn.hoapm.springboot.domain.account.service.impl.UserGetUCImpl;
import vn.hoapm.springboot.domain.account.service.impl.UserImportImpl;
import vn.hoapm.springboot.domain.account.service.impl.UserRegisterUCImpl;
import vn.hoapm.springboot.domain.account.service.usecase.IUserDeleteUC;
import vn.hoapm.springboot.domain.account.service.usecase.IUserGetUC;
import vn.hoapm.springboot.domain.account.service.usecase.IUserImportUC;
import vn.hoapm.springboot.domain.account.service.usecase.IUserRegisterUC;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserResponse findByUsername(UserRequest userRequest) {
        IUserGetUC iUserGetUC = new UserGetUCImpl(userRepository);
        return iUserGetUC.applySearch(userRequest.getUserSearch())
                .findByUsername()
                .endGetByUsername();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserResponse register(UserRequest userRequest) throws CommonException {
        IUserRegisterUC iUserRegisterUC = new UserRegisterUCImpl(userRepository);
        return iUserRegisterUC
                .applyRequest(userRequest.getCud())
                .validateExisting()
                .encodePassword()
                .createPersonalAccount()
                .createRoles()
                .checkInsertSuccess()
                .end();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserResponse> findUsers(UserRequest userRequest) {
        IUserGetUC iUserGetUC = new UserGetUCImpl(userRepository);
        return iUserGetUC
                .applySearch(userRequest.getUserSearch())
                .applyPaging(userRequest.getPagingSortFilter())
                .findUsers()
                .endFindUsers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteUser(UserRequest userRequest) {
        IUserDeleteUC iUserDeleteUC = new UserDeleteUCImpl(userRepository);
        return iUserDeleteUC
                .applyData(userRequest)
                .checkExistedUser(userRequest.getCud().getId())
                .deleteUser()
                .fail()
                .getResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public  List<UserJSONResponse> importUser(String uploadDir) throws IOException {
        IUserImportUC userImportUC = new UserImportImpl(userRepository);
        return userImportUC
                .getUploadDir(uploadDir)
                .convertDataInFileToList()
                .insertBatchUsers()
                .fail()
                .result();
    }
}
