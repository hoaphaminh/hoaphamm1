package vn.hoapm.springboot.infrastructure.shared;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigContainer {
    @Value("${spring.datasource.driver-class}")
    private String propertiesDriverFile;
    public static String propertiesDriver;

    @Value("${spring.datasource.url}")
    private String propertiesUrlFile;
    public static String propertiesUrl;

    @Value("${spring.datasource.username}")
    private String propertiesUsernameFile;
    public static String propertiesUsername;

    @Value("${spring.datasource.password}")
    private String propertiesPasswordFile;
    public static String propertiesPassword;

    @Value("${spring.jpa.show-sql}")
    private String showSql;
    public static String propertiesShowSql;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    public static String propertiesDDLAuto;

    @Value("${spring.jpa.properties.hibernate.order_inserts}")
    private String orderInsert;
    public static String propertiesorderInsert;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;
    public static String propertiesDialect;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String batchSize;
    public static String propertiesBatchSize;


    @Value("${logging.level.org.hibernate.SQL}")
    private String levelSQL;
    public static String propertiesLevelSQL;

    @Value("${logging.level.org.hibernate.type}")
    private String levelType;
    public static String propertiesLevelType;

    @Value("${spring.package-scan}")
    private String packageScan;
    public static String propertiesPackageScan;

    @PostConstruct
    private void init() {
        propertiesDriver = propertiesDriverFile;
        propertiesUrl = propertiesUrlFile;
        propertiesUsername = propertiesUsernameFile;
        propertiesPassword = propertiesPasswordFile;
        propertiesShowSql = showSql;
        propertiesDDLAuto = ddlAuto;
        propertiesorderInsert = orderInsert;
        propertiesDialect = dialect;
        propertiesBatchSize = batchSize;
        propertiesLevelType = levelType;
        propertiesLevelSQL = levelSQL;
        propertiesPackageScan = packageScan;
    }
}
