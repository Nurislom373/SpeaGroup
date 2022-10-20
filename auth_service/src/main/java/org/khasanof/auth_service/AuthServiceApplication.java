package org.khasanof.auth_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.khasanof.auth_service.properties.OpenApiProperties;
import org.khasanof.auth_service.properties.ServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@OpenAPIDefinition
@EnableFeignClients
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
