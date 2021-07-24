package vn.hoapm.springboot.domain.account.service.usecase;


import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;

import java.util.List;

public interface IUserGetUC {
    IUserGetUC applySearch(UserSearch userSearch);

    IUserGetUC applyPaging(PagingSortFilter pagingSortFilter);

    IUserGetUC findUsers();

    IUserGetUC countTotal();

    List<UserResponse> endFindUsers();

    IUserGetUC findByUsername();

    UserResponse endGetByUsername();


}
