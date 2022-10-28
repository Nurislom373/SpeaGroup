package org.khasanof.auth_service.service.auth_token;

import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;

import java.util.List;

public interface AuthTokenRedisService {

    void addRedis(AuthTokenEntity entity);

    void deleteRedis(String id);

    AuthTokenGetDTO getRedis(String id);

    AuthTokenDetailDTO detailRedis(String id);

    List<AuthTokenGetDTO> listRedis();

}
