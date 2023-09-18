package com.sonrisa.timeappointmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
//@EnableSwagger2
public class TimeAppointmentAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeAppointmentAppApplication.class, args);
    }

}
