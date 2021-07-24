package vn.hoapm.springboot.infrastructure.shared;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InfraConfig {

    @Bean
    public DataSource mySqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://hoapm.csfo76trwzyq.us-east-2.rds.amazonaws.com:3306/Spring");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/spring");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("hoa123456");


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
