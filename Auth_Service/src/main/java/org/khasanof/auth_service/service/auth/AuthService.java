package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.dto.auth.AuthDto;
import org.khasanof.auth_service.dto.auth.PasswordChangesDto;
import org.khasanof.auth_service.dto.auth.SessionDto;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements BaseService {
    public ResponseEntity<Data<SessionDto>> getToken(AuthDto dto) {
        return null;
    }

    public ResponseEntity<Data<Void>> changePassword(PasswordChangesDto dto) {
        return null;
    }
}
