package com.RestFullAPI.BuildRestAPI.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI MySwaggerConfig(){
        return new OpenAPI()
                .info(
                new Info().title("Student Management System ..!")
                        .description("By Harsh Vathere")
                )
                .servers(List.of(new Server().url("http://localhost:8080").description("local"),
                        new Server().url("http://localhost:8081").description("Live")));

    }
}
