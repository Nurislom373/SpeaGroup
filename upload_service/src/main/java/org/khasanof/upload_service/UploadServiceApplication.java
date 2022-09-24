package org.khasanof.upload_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class UploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadServiceApplication.class, args);
    }

}
