package vn.hoapm.springboot.infrastructure.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InfraConfig {

    @Autowired
    public ConfigContainer configContainer;

    @Bean
    public DataSource mySqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName(ConfigContainer.propertiesDriver);
//        dataSourceBuilder.url("jdbc:mysql://hoapm.csfo76trwzyq.us-east-2.rds.amazonaws.com:3306/Spring");
        dataSourceBuilder.url(ConfigContainer.propertiesUrl);
        dataSourceBuilder.username(ConfigContainer.propertiesUsername);
        dataSourceBuilder.password(ConfigContainer.propertiesPassword);


//        dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSourceBuilder.url("jdbc:oracle:thin:@//localhost/XE");
//        dataSourceBuilder.username("admin");
//        dataSourceBuilder.password("admin");

//        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSourceBuilder.url("jdbc:sqlserver://DESKTOP-M8ASORT;databaseName=hoapmSpring");
//        dataSourceBuilder.username("hoapm");
//        dataSourceBuilder.password("123456");

        return dataSourceBuilder.build();
    }


}
