package vn.hoapm.springboot.infrastructure.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "vn.hoapm")
public class InfraConfig {

    @Autowired
    public ConfigContainer configContainer;

    @Bean
    public DataSource mySqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(ConfigContainer.propertiesDriver);
        dataSourceBuilder.url(ConfigContainer.propertiesUrl);
        dataSourceBuilder.username(ConfigContainer.propertiesUsername);
        dataSourceBuilder.password(ConfigContainer.propertiesPassword);
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(mySqlDataSource());
        sessionFactory.setPackagesToScan(ConfigContainer.propertiesPackageScan);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("spring.jpa.show-sql", ConfigContainer.propertiesShowSql);
        hibernateProperties.put("spring.jpa.hibernate.ddl-auto", ConfigContainer.propertiesDDLAuto);
        hibernateProperties.put("spring.jpa.properties.hibernate.order_inserts", ConfigContainer.propertiesorderInsert);
        hibernateProperties.put("spring.jpa.properties.hibernate.dialect", ConfigContainer.propertiesDialect);
        hibernateProperties.put("spring.jpa.properties.hibernate.jdbc.batch_size", ConfigContainer.propertiesBatchSize);
        hibernateProperties.put("logging.level.org.hibernate.SQL", ConfigContainer.propertiesLevelSQL);
        hibernateProperties.put("logging.level.org.hibernate.type", ConfigContainer.propertiesLevelType);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }


}
