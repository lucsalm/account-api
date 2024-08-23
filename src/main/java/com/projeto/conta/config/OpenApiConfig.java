package com.projeto.conta.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class OpenApiConfig {


    private static final String FILE_SOURCE = "static/files/DETAILS.md";


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
            Path path = Paths.get(getClass().getClassLoader().getResource(FILE_SOURCE).toURI());
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "API de operações de conta, transações de crédito e débito e extrato";

        }
    }

}