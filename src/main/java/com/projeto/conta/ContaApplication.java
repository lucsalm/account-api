package com.projeto.conta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class ContaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContaApplication.class, args);
    }


}
