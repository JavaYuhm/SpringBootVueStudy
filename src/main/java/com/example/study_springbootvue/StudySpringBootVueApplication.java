package com.example.study_springbootvue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class StudySpringBootVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringBootVueApplication.class, args);
    }

}
