package vn.hoapm.springboot.infrastructure.user.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;
import vn.hoapm.springboot.infrastructure.user.factory.UserDB;

import javax.sql.DataSource;
import java.sql.Types;

public class CreateAccount extends SqlUpdate implements BaseQuery {

    public CreateAccount(DataSource dataSource) {
        super();
        this.setDataSource(dataSource);
        this.declareParameters();
        this.setSql(buildSQL());
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
                .append("  :NAME")
                .append(", :PHONE")
                .append(", :EMAIL")
                .append(", :USERNAME")
                .append(", :PASSWORD ) ");
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


    public long execute(Object... params) throws DataAccessException {
        UserCUD userCUD = (UserCUD) params[0];
        MapSqlParameterSource paramNamed = new MapSqlParameterSource();
        paramNamed.addValue(UserDB.NAME, userCUD.getName());
        paramNamed.addValue(UserDB.PHONE, userCUD.getPhone());
        paramNamed.addValue(UserDB.EMAIL, userCUD.getEmail());
        paramNamed.addValue(UserDB.USERNAME, userCUD.getUsername());
        paramNamed.addValue(UserDB.PASSWORD, userCUD.getPassword());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int executedRecord = this.updateByNamedParam(paramNamed.getValues(),keyHolder);
        return executedRecord > 0 ? keyHolder.getKey().longValue() : 0l;
    }
}
