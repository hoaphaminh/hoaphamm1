package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;


import javax.sql.DataSource;
import java.sql.Types;

/**
 * @ClassName :DeleteUser
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/18/2021 3:33 PM
 */
public class DeleteUser extends SqlUpdate implements BaseQuery {
    public DeleteUser(DataSource dataSource){
        super();
        this.setDataSource(dataSource);
        this.setSql(buildSQL());
        this.declareParameters();
        this.compile();
    }
    @Override
    public String buildSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE USERS u")
                .append(" SET u.ENABLE = 0 ,")
                .append(" u.UTIMESTAMP = CURRENT_TIMESTAMP()")
                .append(" WHERE u.ID = :"+ UserDB.ID)
                .append(" AND u.UTIMESTAMP = :"+UserDB.UTIMESTAMP);
        return stringBuilder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.ID, Types.NUMERIC));
        declareParameter(new SqlParameter(UserDB.UTIMESTAMP, Types.TIMESTAMP));
    }

    public int executed(UserCUD userCUD) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(UserDB.ID, userCUD.getId());
        map.addValue(UserDB.UTIMESTAMP, userCUD.getUtimestamp());
        return super.updateByNamedParam(map.getValues());
    }
}
