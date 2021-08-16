package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InsertBatchUsers extends SqlUpdate implements BaseQuery {
    public InsertBatchUsers(DataSource dataSource) {
        super();
        this.setSql(buildSQL());
        this.setDataSource(dataSource);
        this.declareParameters();
        this.setGeneratedKeysColumnNames("ID");
        this.setReturnGeneratedKeys(true);
        this.compile();
    }

    @Override
    public String buildSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO users (")
                .append("  NAME, PHONE, EMAIL, USERNAME, PASSWORD ")
                .append(" ) VALUES ( ")
                .append("  ?")
                .append(", ?")
                .append(", ?")
                .append(", ?")
                .append(", ? ) ");
        return builder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(UserDB.NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.PHONE, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.EMAIL, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.USERNAME, Types.VARCHAR));
        declareParameter(new SqlParameter(UserDB.PASSWORD, Types.VARCHAR));
    }

    public int[] execute(List<UserCUD> importUsers) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        List<Object[]> mappingParameters = importUsers.stream().map(e ->
        {
            List<SqlParameterValue> sqlParameterValues = new ArrayList<>();
            SqlParameterValue sqlParameterValueName = new SqlParameterValue(Types.VARCHAR, UserDB.NAME,e.getName());
            SqlParameterValue sqlParameterValuePhone = new SqlParameterValue(Types.VARCHAR, UserDB.PHONE, e.getPhone());
            SqlParameterValue sqlParameterValueEmail = new SqlParameterValue(Types.VARCHAR, UserDB.EMAIL, e.getEmail());
            SqlParameterValue sqlParameterValueUsername = new SqlParameterValue(Types.VARCHAR,UserDB.USERNAME, e.getUsername());
            SqlParameterValue sqlParameterValuePassword = new SqlParameterValue(Types.VARCHAR, UserDB.PASSWORD,e.getPassword());
            sqlParameterValues.add(sqlParameterValueName);
            sqlParameterValues.add(sqlParameterValuePhone);
            sqlParameterValues.add(sqlParameterValueEmail);
            sqlParameterValues.add(sqlParameterValueUsername);
            sqlParameterValues.add(sqlParameterValuePassword);
            return sqlParameterValues.toArray();
        }).collect(Collectors.toList());
        return this.getJdbcTemplate().batchUpdate(buildSQL(), mappingParameters);
    }
}
