package vn.hoapm.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HoapmSpringApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(HoapmSpringApplication.class, args);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(passwordEncoder.encode("123456"));
    }
}
