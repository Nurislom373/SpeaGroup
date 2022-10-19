package org.khasanof.auth_service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.khasanof.auth_service.response.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.webjars.NotFoundException;

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

    public static Data<Object> callGetAPI(String path, String notFoundMessage, TypeReference<Data<Object>> reference) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(path));
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) throw new NotFoundException(notFoundMessage);
            return objectMapper.readValue(httpResponse.getEntity().getContent(), reference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
