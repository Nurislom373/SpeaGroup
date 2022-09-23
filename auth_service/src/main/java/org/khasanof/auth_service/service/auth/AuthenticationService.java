package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.dto.token.TokenDTO;
import org.khasanof.auth_service.service.BaseService;

public interface AuthenticationService extends BaseService {

    TokenDTO login(AuthRequestDTO dto);

}
