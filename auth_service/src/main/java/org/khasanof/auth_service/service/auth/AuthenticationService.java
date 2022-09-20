package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.service.BaseService;

public interface AuthenticationService extends BaseService {

    String login(AuthRequestDTO dto);

    void register(AuthUserCreateDTO dto);

}
