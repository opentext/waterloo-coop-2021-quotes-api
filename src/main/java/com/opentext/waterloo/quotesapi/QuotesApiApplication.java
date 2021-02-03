package com.opentext.waterloo.quotesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class QuotesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApiApplication.class, args);
    }

}
