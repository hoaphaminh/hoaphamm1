package vn.hoapm.springboot.infrastructure.documentstorage.sql;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.infrastructure.documentstorage.factory.DocumentStorageDB;
import vn.hoapm.springboot.infrastructure.shared.BaseQuery;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName :CreateDS
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/20/2021 7:43 PM
 */
public class CreateDS extends SqlUpdate implements BaseQuery {
    public CreateDS(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(buildSQL());
        this.declareParameters();
        this.setReturnGeneratedKeys(true);
        this.setGeneratedKeysColumnNames("ID");
        this.compile();
    }

    @Override
    public String buildSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(" INSERT INTO DOCUMENTSTORAGE (USER_ID, FILE_NAME, DOC_TYPE,DOC_FORMAT, UPLOAD_DIR) VALUES ")
                .append(" (:" + DocumentStorageDB.USER_ID)
                .append(" ,:" + DocumentStorageDB.FILE_NAME)
                .append(" ,:" + DocumentStorageDB.DOC_TYPE)
                .append(" ,:" + DocumentStorageDB.DOC_FORMAT)
                .append(" ,:" + DocumentStorageDB.UPLOAD_DIR + ")");
        return builder.toString();
    }

    @Override
    public void declareParameters() {
        declareParameter(new SqlParameter(DocumentStorageDB.USER_ID, Types.NUMERIC));
        declareParameter(new SqlParameter(DocumentStorageDB.FILE_NAME, Types.VARCHAR));
        declareParameter(new SqlParameter(DocumentStorageDB.DOC_TYPE, Types.VARCHAR));
        declareParameter(new SqlParameter(DocumentStorageDB.DOC_FORMAT, Types.VARCHAR));
        declareParameter(new SqlParameter(DocumentStorageDB.UPLOAD_DIR, Types.VARCHAR));
    }

    public long execute(DSRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put(DocumentStorageDB.USER_ID, request.getUserId());
        map.put(DocumentStorageDB.FILE_NAME, request.getFileName());
        map.put(DocumentStorageDB.DOC_TYPE, request.getDocType());
        map.put(DocumentStorageDB.DOC_FORMAT, request.getDocFormat());
        map.put(DocumentStorageDB.UPLOAD_DIR, request.getUploadDir());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int executedRecord = this.updateByNamedParam(map,keyHolder);
        return executedRecord > 0 ? keyHolder.getKey().longValue() : 0l;
    }
}
