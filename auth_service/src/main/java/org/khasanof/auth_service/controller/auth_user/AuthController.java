package org.khasanof.auth_service.controller.auth_user;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.auth_user.AuthDTO;
import org.khasanof.auth_service.dto.auth_user.PasswordChangesDTO;
import org.khasanof.auth_service.dto.auth_user.SessionDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_user.AuthService;
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
    public ResponseEntity<Data<SessionDTO>> getToken(@RequestBody AuthDTO dto) {
        return service.getToken(dto);
    }


    @RequestMapping(value = PATH + "/employee/change_password",method = RequestMethod.PATCH)
    public ResponseEntity<Data<Void>> changePassword(@RequestBody PasswordChangesDTO dto) {
        return service.changePassword(dto);
    }
}
