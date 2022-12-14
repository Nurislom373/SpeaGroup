package org.khasanof.auth_service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class BaseUtils {

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(4);

    public static final List<String> DEFAULT_CATEGORIES = List.of(
            "6325ee332088c03f152df32f",
            "6325ee332088c03f152df330",
            "6325ee332088c03f152df331");

    public <T> List<T> jsonParseObject(String resourceUrl, T entity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<T>> typeReference = new TypeReference<>() {
            };

            InputStream inputStream = getClass().getResourceAsStream(resourceUrl);

            return objectMapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Instant currentTimeAddMinute(Integer minute) {
        return Instant.ofEpochMilli(System.currentTimeMillis() + (minute * 60 * 1000));
    }
}
