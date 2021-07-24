package vn.hoapm.springboot.infrastructure.user.factory;

import lombok.Data;

@Data

public class UserDB {
    private UserDB(){}
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PHONE = "PHONE";
    public static final String EMAIL = "EMAIL";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String UTIMESTAMP = "UTIMESTAMP";
    public static final String CREATED_AT = "CREATED_AT";
    public static final String USER_ID = "USER_ID";
    public static final String ROLE_NAME = "ROLE_NAME";
    public static final String ROLE_CODE = "ROLE_CODE";
    public static final String OFFSET = "OFFSET";
    public static final String LIMIT = "LIMIT";
    public static final String ORDER_BY = "ORDER_BY";
}
