package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthChangeImagePathDTO;
import org.khasanof.auth_service.dto.auth.AuthChangePasswordDTO;
import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.service.BaseService;

import java.util.Map;

public interface AuthenticationService extends BaseService {

    Map<String, String> login(AuthRequestDTO dto);

    void verifiedEmail(String userId);

    void changePassword(AuthChangePasswordDTO dto);

    void changeImagePath(AuthChangeImagePathDTO dto);

}
