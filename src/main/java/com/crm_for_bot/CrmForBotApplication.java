package com.crm_for_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmForBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmForBotApplication.class, args);
        System.out.println("http://localhost:9000/login");
    }

}
