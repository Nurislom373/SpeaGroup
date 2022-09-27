package org.khasanof.api_gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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
				.route(p -> p.path("/api/v1/**")
						.filters(f -> f.filter(authenticationFilter))
						.uri("lb://auth-service"))
				.build();
	}

}

@Component
@RequiredArgsConstructor
class AuthenticationFilter implements GatewayFilter {

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

			this.populateRequestWithHeaders(exchange, token);
		}
		return chain.filter(exchange);
	}


	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
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

@Component
class RouterValidator {
	public static final List<String> WHITE_LIST = List.of(
			"/api/v1/auth/login",
			"/api/v1/auth/register");

	public Predicate<ServerHttpRequest> isSecured =
			request ->
					WHITE_LIST.stream()
							.noneMatch(uri -> request.getURI()
									.getPath()
									.contains(uri));
}

@Component
class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private boolean isTokenExpired(String token) {
		return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
	}

	public boolean isInvalid(String token) {
		return this.isTokenExpired(token);
	}

}