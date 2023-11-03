package com.dulana.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiesRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiesRegisterApplication.class, args);
    }

}
