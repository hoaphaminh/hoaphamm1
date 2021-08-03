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


    @PostConstruct
    private void init() {
        this.propertiesDriver = propertiesDriverFile;
        this.propertiesUrl = propertiesUrlFile;
        this.propertiesUsername = propertiesUsernameFile;
        this.propertiesPassword = propertiesPasswordFile;
    }
}
