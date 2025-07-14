package com.example.radiationmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main entry point for the Spring Boot application.
 * The @SpringBootApplication annotation enables a host of features,
 * such as component scanning and auto-configuration.
 */
@SpringBootApplication
@EnableScheduling // needed later for data generation.
public class RadiationMonitorApplication {

    public static void main(String[] args) {
        // starts the entire Spring application.
        SpringApplication.run(RadiationMonitorApplication.class, args);
    }
}
