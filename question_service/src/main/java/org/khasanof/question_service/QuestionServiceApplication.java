package org.khasanof.question_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/4/2022
 * <br/>
 * Time: 7:12 PM
 * <br/>
 * Package: org.khasanof.question_service
 */
@EnableFeignClients("org.khasanof")
@OpenAPIDefinition
@SpringBootApplication
//@ImportAutoConfiguration({FeignAutoConfiguration.class}) Spring Boot 3.0.0 version
public class QuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
    }

}
