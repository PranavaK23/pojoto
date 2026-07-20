package com.yourapp.entertainmenthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EntertainmentHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(EntertainmentHubApplication.class, args);
    }
}
