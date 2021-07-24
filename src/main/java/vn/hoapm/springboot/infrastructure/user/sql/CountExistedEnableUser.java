package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.MappingSqlQuery;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.shared.Total;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * @ClassName :CountExistedEnableUser
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/18/2021 3:33 PM
 */
public class CountExistedEnableUser extends MappingSqlQuery implements BaseQuery {
    public CountExistedEnableUser(DataSource dataSource) {
        super();
        this.setDataSource(dataSource);
        this.setSql(buildSQL());
        this.declareParameters();
        this.compile();
    }

    @Override
    protected Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getInt(Total.TOTAL);
    }

    @Override
    public String buildSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT COUNT(u.ID) TOTAL")
                .append(" FROM USERS u ")
                .append(" WHERE  u.ID = :" + UserDB.ID + " AND u.ENABLE = 1");
        return stringBuilder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.ID, Types.NUMERIC));
    }

    public int execute(Long id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue(UserDB.ID, id);
        List<Object> rs = super.executeByNamedParam(map.getValues());
        return (int) rs.get(0);
    }

}
