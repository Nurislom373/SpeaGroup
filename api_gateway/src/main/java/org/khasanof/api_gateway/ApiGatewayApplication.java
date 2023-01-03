package org.khasanof.api_gateway;

import lombok.RequiredArgsConstructor;
import org.khasanof.api_gateway.config.AdminAuthenticationFilter;
import org.khasanof.api_gateway.config.AuthenticationFilter;
import org.khasanof.api_gateway.config.BaseUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path(BaseUtils.AUTH_SERVICE_PATHS)
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8800"))
                .route(p -> p.path(BaseUtils.QUESTION_SERVICE_PATHS)
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("http://localhost:8803"))
                .route(p -> p.path(BaseUtils.POST_SERVICE_PATHS)
                        .uri("http://localhost:8802"))
                .route(p -> p.path(BaseUtils.UPLOAD_SERVICE_PATHS)
                        .uri("http://localhost:8801"))
                .build();
    }

}