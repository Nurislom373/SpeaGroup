package org.khasanof.auth_service.service.auth_user;

import org.khasanof.auth_service.dto.auth_user.AuthDTO;
import org.khasanof.auth_service.dto.auth_user.PasswordChangesDTO;
import org.khasanof.auth_service.dto.auth_user.SessionDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements BaseService {
    public ResponseEntity<Data<SessionDTO>> getToken(AuthDTO dto) {
        return null;
    }

    public ResponseEntity<Data<Void>> changePassword(PasswordChangesDTO dto) {
        return null;
    }
}
