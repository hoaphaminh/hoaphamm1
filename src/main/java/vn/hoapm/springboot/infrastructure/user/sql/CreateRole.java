package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import vn.hoapm.springboot.domain.account.factory.User;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;

import javax.sql.DataSource;
import java.sql.Types;

public class CreateRole extends SqlUpdate implements BaseQuery {

    public CreateRole(DataSource dataSource) {
        super();
        this.setDataSource(dataSource);
        this.declareParameters();
        this.setSql(buildSQL());
        this.compile();
    }

    @Override
    public String buildSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO user_roles (")
                .append("  USER_ID, ROLE_CODE  ")
                .append(" ) VALUES ( ")
                .append("  :USER_ID")
                .append(", :ROLE_CODE ) ");
        return builder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.USER_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(UserDB.ROLE_CODE, Types.VARCHAR));

    }

    public int execute(Object... params) throws DataAccessException {
        User user = (User) params[0];
        MapSqlParameterSource paramNamed = new MapSqlParameterSource();
        paramNamed.addValue(UserDB.USER_ID, user.getId());
        paramNamed.addValue(UserDB.ROLE_CODE, user.getRoleCode());
        return this.updateByNamedParam(paramNamed.getValues());
    }
}
