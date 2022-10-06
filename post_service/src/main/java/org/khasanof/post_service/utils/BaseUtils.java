package org.khasanof.post_service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.khasanof.post_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.post_service.response.Data;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class BaseUtils {
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;

    public static final String AUTH_SERVICE = "http://localhost:8800/" + PATH ;

    public static AuthUserGetDTO callGetAPI(String path, String notFoundMessage) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper objectMapper = new ObjectMapper();
            CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(path));
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode != 200) throw new NotFoundException(notFoundMessage);
            return objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Data<AuthUserGetDTO>>() {
            }).getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
