package com.projeto.conta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Account API", description = "API de contas para transações e extrato",version = "1.0.0"))
public class ContaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContaApplication.class, args);
    }


}
