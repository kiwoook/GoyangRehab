package com.study.goyangrehab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoyangehabApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoyangehabApplication.class, args);
    }

}
