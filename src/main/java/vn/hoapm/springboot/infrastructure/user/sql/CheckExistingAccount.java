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

public class CheckExistingAccount extends MappingSqlQuery implements BaseQuery {

    public CheckExistingAccount(DataSource dataSource) {
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
                .append(" FROM users u ")
                .append(" WHERE ")
                .append(" ( :" + UserDB.PHONE + " IS NULL OR u.PHONE = :" + UserDB.PHONE + " )")
                .append(" OR (:" + UserDB.USERNAME + " IS NULL OR u.USERNAME = :" + UserDB.USERNAME + " )")
                .append(" OR (:" + UserDB.EMAIL + " IS NULL OR u.EMAIL = :" + UserDB.EMAIL + " )");
        return stringBuilder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.PHONE, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.EMAIL, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.USERNAME, Types.VARCHAR));
    }

    public int execute(String checkString, boolean checkExistingUsername, boolean checkExistingPhone,
            boolean checkExistingEmail) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        if (checkExistingUsername) {
            map.addValue(UserDB.USERNAME, checkString);
            map.addValue(UserDB.EMAIL, "");
            map.addValue(UserDB.PHONE, "");
        } else if (checkExistingEmail) {
            map.addValue(UserDB.EMAIL, checkString);
            map.addValue(UserDB.USERNAME, "");
            map.addValue(UserDB.PHONE, "");
        } else if (checkExistingPhone) {
            map.addValue(UserDB.PHONE, checkString);
            map.addValue(UserDB.EMAIL, "");
            map.addValue(UserDB.USERNAME, "");
        }
        List<Object> rs = super.executeByNamedParam(map.getValues());
        return (int) rs.get(0);
    }
}
