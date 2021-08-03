package vn.hoapm.springboot.infrastructure.documentstorage;

import org.springframework.stereotype.Repository;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.domain.documentstorage.repository.IDSRepository;
import vn.hoapm.springboot.infrastructure.documentstorage.sql.CreateDS;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class DSRepositoryImpl implements IDSRepository {
    private final DataSource dataSource;
    private CreateDS createDS;

    public DSRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @PostConstruct
    public void compiler(){
        createDS = new CreateDS(dataSource);
    }


    @Override
    public long createDS(DSRequest request) {
        return createDS.execute(request);
    }
}
