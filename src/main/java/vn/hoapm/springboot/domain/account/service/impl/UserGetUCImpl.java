package vn.hoapm.springboot.domain.account.service.impl;

import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.account.service.usecase.IUserGetUC;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;

import java.util.List;

public class UserGetUCImpl implements IUserGetUC {
    private UserResponse userResponse;
    private UserSearch userSearch;
    private PagingSortFilter pagingSortFilter;
    private List<UserResponse> userResponses;
    private final UserRepository repository;


    public UserGetUCImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public IUserGetUC applySearch(UserSearch userSearch) {
        this.userSearch = userSearch;
        return this;
    }

    @Override
    public IUserGetUC applyPaging(PagingSortFilter pagingSortFilter) {
        this.pagingSortFilter = pagingSortFilter;
        return this;
    }


    @Override
    public IUserGetUC findUsers() {
        userResponses = repository.findUsers(userSearch,pagingSortFilter);
        return this;
    }


    @Override
    public IUserGetUC countTotal() {
        return this;
    }

    @Override
    public List<UserResponse> endFindUsers() {
        return userResponses;
    }

    @Override
    public IUserGetUC findByUsername() {
        this.userResponse = repository.findBindUsername(userSearch);
        return this;
    }

    @Override
    public UserResponse endGetByUsername() {
        return this.userResponse;
    }
}
