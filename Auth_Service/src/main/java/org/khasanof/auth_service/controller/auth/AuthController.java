package org.khasanof.auth_service.controller.auth;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.auth.AuthDto;
import org.khasanof.auth_service.dto.auth.PasswordChangesDto;
import org.khasanof.auth_service.dto.auth.SessionDto;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController extends AbstractController<AuthService> {
    public AuthController(AuthService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/auth/token", method = RequestMethod.POST)
    public ResponseEntity<Data<SessionDto>> getToken(@RequestBody AuthDto dto) {
        return service.getToken(dto);
    }


    @RequestMapping(value = PATH + "/employee/change_password",method = RequestMethod.PATCH)
    public ResponseEntity<Data<Void>> changePassword(@RequestBody PasswordChangesDto dto) {
        return service.changePassword(dto);
    }
}
