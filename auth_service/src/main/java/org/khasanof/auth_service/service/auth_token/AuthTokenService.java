package org.khasanof.auth_service.service.auth_token;


import org.khasanof.auth_service.criteria.auth_token.AuthTokenCriteria;
import org.khasanof.auth_service.criteria.auth_token.AuthTokenTypeCriteria;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

import java.util.List;

public interface AuthTokenService extends GenericGDLService<AuthTokenGetDTO, AuthTokenDetailDTO, String, AuthTokenCriteria>,
        AuthTokenRedisService,
        BaseService {

    void create(AuthTokenCreateDTO dto);

    void delete(String id);

    List<AuthTokenGetDTO> listType(AuthTokenTypeCriteria criteria);

    long count();

    void autoIsDead();
}
