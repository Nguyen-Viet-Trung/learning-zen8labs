package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(new Info().title("Journal App APIs").description("Trung test swagger"))
        .servers(Arrays.asList(new Server().url("http://localhost:8080").description("local"), new Server().url("http://localhost:8081").description("dev")))
        .addSecurityItem(new SecurityRequirement().addList("bearerAtuh"))
        .components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .in(SecurityScheme.In.HEADER)
        .name("Authorization")));
    }
}
