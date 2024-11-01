package com.purepoint.springbootpurepoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class SpringbootPurepointApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPurepointApplication.class, args);
    }

}