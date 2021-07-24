package vn.hoapm.springboot.infrastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.hoapm.springboot.domain.account.factory.User;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.repository.UserRepository;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;
import vn.hoapm.springboot.infrastructure.user.sql.*;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final DataSource dataSource;
    private FindUserByUsername findUserByUsername;
    private CreateAccount createAccount;
    private CreateRole createRole;
    private FindUsers findUsers;
    private CountExistedEnableUser countExistedEnableUser;
    private DeleteUser deleteUser;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void compileSQL() {
        findUserByUsername = new FindUserByUsername(dataSource);
        createAccount = new CreateAccount(dataSource);
        createRole = new CreateRole(dataSource);
        findUsers = new FindUsers(dataSource);
        countExistedEnableUser = new CountExistedEnableUser(dataSource);
        deleteUser = new DeleteUser(dataSource);

    }

    @Override
    public UserResponse findBindUsername(UserSearch search) {
        List<UserResponse> result = findUserByUsername.execute(search);
        return !result.isEmpty() ? result.get(0) : null;
    }

    @Override
    public long create(UserCUD userCUD) {
        return createAccount.execute(userCUD);
    }

    @Override
    public int createRole(User user) {
        return createRole.execute(user);
    }

    @Override
    public List<UserResponse> findUsers(UserSearch search, PagingSortFilter pagingSortFilter) {
        return findUsers.execute(search, pagingSortFilter);
    }

    @Override
    public int countExistedEnabledRecord(Long id) {
        return countExistedEnableUser.execute(id);
    }

    @Override
    public int deleteUser(UserCUD userCUD) {
        return deleteUser.executed(userCUD);
    }


}
