package org.khasanof.auth_service.controller.auth_controller;

import org.apache.kafka.common.protocol.types.Field;
import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.auth.AuthChangeImagePathDTO;
import org.khasanof.auth_service.dto.auth.AuthChangePasswordDTO;
import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.token.TokenDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth.AuthenticationService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth/*")
public class AuthenticationController extends AbstractController<AuthenticationService> {

    public AuthenticationController(AuthenticationService service) {
        super(service);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Data<TokenDTO>> login(@RequestBody AuthRequestDTO dto) {
        return new ResponseEntity<>(new Data<>(service.login(dto)), HttpStatus.OK);
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> changePassword(@RequestBody AuthChangePasswordDTO dto) {
        service.changePassword(dto);
        return new ResponseEntity<>(new Data<>("Successfully Changed Password"), HttpStatus.OK);
    }

    @RequestMapping(value = "changeImage", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> changeImage(@RequestBody AuthChangeImagePathDTO dto) {
        service.changeImagePath(dto);
        return new ResponseEntity<>(new Data<>("Successfully Changed Image"), HttpStatus.OK);
    }


}
