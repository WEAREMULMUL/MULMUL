package com.excmul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ExcmulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcmulApplication.class, args);
    }
}
