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

@Getter
@Setter
public class BaseUtils {

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;

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
