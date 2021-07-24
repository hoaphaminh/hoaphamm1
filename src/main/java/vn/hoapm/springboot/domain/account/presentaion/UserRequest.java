package vn.hoapm.springboot.domain.account.presentaion;

import lombok.Data;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;


@Data
public class UserRequest {
    private UserSearch userSearch;
    private UserCUD cud;
    private PagingSortFilter pagingSortFilter;
}
