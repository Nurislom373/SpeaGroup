package org.khasanof.auth_service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Getter
@Setter
public class BaseUtils {
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

    public static String encode(String pwd) {
        return new String(Base64.encodeBase64(pwd.getBytes()));
    }

    public static String decode(String pwd) {
        return new String(Base64.decodeBase64(pwd.getBytes()));
    }

    public static boolean match(String pwd, String hashPwd) {
        String decodedString = new String(Base64.decodeBase64(hashPwd.getBytes()));
        return pwd.equals(decodedString);
    }
}
