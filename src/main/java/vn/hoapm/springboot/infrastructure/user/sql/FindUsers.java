package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.shared.Constants;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * @ClassName :FindUsers
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :6/14/2021 10:47 PM
 */
public class FindUsers extends MappingSqlQuery<UserResponse> implements BaseQuery {
    public FindUsers(DataSource dataSource) {
        super();
        this.setDataSource(dataSource);
        this.setSql(buildSQL());
        this.declareParameters();
        this.compile();
    }

    @Override
    protected UserResponse mapRow(ResultSet rs, int i) throws SQLException {
        return UserResponse
                .builder()
                .id(rs.getLong(UserDB.ID))
                .name(rs.getString(UserDB.NAME))
                .username(rs.getString(UserDB.USERNAME))
                .phone(rs.getString(UserDB.PHONE))
                .role(rs.getString(UserDB.ROLE_NAME))
                .password(rs.getString(UserDB.PASSWORD))
                .createdAt(rs.getTimestamp(UserDB.CREATED_AT))
                .build();
    }

    @Override
    public String buildSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT u.ID,")
                .append(" u.NAME,")
                .append(" USERNAME,")
                .append(" PHONE,")
                .append(" PASSWORD,")
                .append(" r.NAME ROLE_NAME,")
                .append(" u.CREATED_AT ")
                .append(" FROM users u ")
                .append(" LEFT JOIN user_roles ur on u.ID = ur.USER_ID ")
                .append(" LEFT JOIN roles r on r.CODE = ur.ROLE_CODE ")
                .append(" WHERE (:USERNAME IS NULL OR LOWER(u.USERNAME) = LOWER( :" + UserDB.USERNAME + " )) ")
                .append(" AND (:NAME IS NULL OR LOWER(u.NAME) LIKE LOWER('%' || :" + UserDB.NAME + " || '%' )) ")
                .append(" AND u.ENABLE = 1 ")
                .append(" ORDER BY CREATED_AT ")
                .append(" LIMIT  :" + UserDB.LIMIT)
                .append(" OFFSET :" + UserDB.OFFSET);

        return builder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.USERNAME, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.OFFSET, Types.NUMERIC));
        declareParameter(new SqlParameter(UserDB.LIMIT, Types.NUMERIC));
    }

    public List<UserResponse> execute(UserSearch search, PagingSortFilter pagingSortFilter) throws DataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(UserDB.USERNAME, search.getUsername());
        map.addValue(UserDB.NAME, search.getName());
        int pagesize = pagingSortFilter.getPageSize() != null ? pagingSortFilter.getPageSize() : Constants.DEFAULT_PAGE_SIZE;
        int pageIndex = pagingSortFilter.getPageIndex() != null ? pagingSortFilter.getPageIndex() : Constants.DEFAULT_PAGE_INDEX;
        map.addValue(UserDB.LIMIT, pagesize);
        map.addValue(UserDB.OFFSET, (pageIndex - 1) >= 0 ? (pageIndex - 1) * pagesize : Constants.DEFAULT_PAGE_SIZE);
        return super.executeByNamedParam(map.getValues());
    }
}
