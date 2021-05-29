package com.example.dyplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableScheduling
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class DyplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyplomApplication.class, args);
    }

}
