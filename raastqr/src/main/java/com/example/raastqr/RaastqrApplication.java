package com.example.raastqr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RaastqrApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaastqrApplication.class, args);
    }

}
