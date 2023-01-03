package org.khasanof.api_gateway.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/3/2023
 * <br/>
 * Time: 9:30 PM
 * <br/>
 * Package: org.khasanof.api_gateway.config
 */
@Component
@RequiredArgsConstructor
public class AdminAuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            String token = this.getAuthHeader(request);
            token = token.substring(7);

            if (jwtUtil.isInvalid(token))
                return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            if (getRole(token).equals("Admin")) {
                return this.onError(exchange, "Permission Denied!", HttpStatus.FORBIDDEN);
            }

            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getRole(String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        return (String) claims.get("role");
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.getSubject()))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}
