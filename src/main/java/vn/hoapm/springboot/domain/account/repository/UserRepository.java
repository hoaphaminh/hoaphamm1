package vn.hoapm.springboot.domain.account.repository;
import vn.hoapm.springboot.domain.account.factory.User;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;

import java.util.List;


public interface UserRepository {
    UserResponse findBindUsername(UserSearch search);

    long create(UserCUD userCUD);

    int createRole(User user);

    List<UserResponse> findUsers (UserSearch search, PagingSortFilter pagingSortFilter);

    int countExistedEnabledRecord(Long id);

    int deleteUser(UserCUD userCUD);
}
