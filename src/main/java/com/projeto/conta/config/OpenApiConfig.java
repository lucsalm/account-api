package com.projeto.conta.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class OpenApiConfig {


    private static final String FILE_SOURCE = "src/main/resources/static/files/DETAILS.md";


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account API")
                        .version("1.0.0")
                        .description(getReadmeContent())
                );

    }

    private String getReadmeContent() {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_SOURCE)));
        } catch (IOException e) {
            return "API de operações de conta, transações de crédito e débito e extrato";

        }
    }

}