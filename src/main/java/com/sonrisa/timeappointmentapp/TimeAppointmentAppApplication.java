package com.sonrisa.timeappointmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TimeAppointmentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeAppointmentAppApplication.class, args);
    }

}
