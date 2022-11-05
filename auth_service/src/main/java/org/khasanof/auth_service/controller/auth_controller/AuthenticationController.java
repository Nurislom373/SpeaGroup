package org.khasanof.auth_service.controller.auth_controller;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.auth.AuthChangeImagePathDTO;
import org.khasanof.auth_service.dto.auth.AuthChangePasswordDTO;
import org.khasanof.auth_service.dto.auth.AuthRequestDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth.AuthenticationService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth/*")
public class AuthenticationController extends AbstractController<AuthenticationService> {

    public AuthenticationController(AuthenticationService service) {
        super(service);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Data<Map<String, String>>> login(@Valid @RequestBody AuthRequestDTO dto) {
        return new ResponseEntity<>(new Data<>(service.login(dto)), HttpStatus.OK);
    }

    @RequestMapping(value = "verifiedEmail/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> verifiedEmail(@PathVariable String id) {
        service.verifiedEmail(id);
        return new ResponseEntity<>(new Data<>("Successfully Verified Email"), HttpStatus.OK);
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> changePassword(@Valid @RequestBody AuthChangePasswordDTO dto) {
        service.changePassword(dto);
        return new ResponseEntity<>(new Data<>("Successfully Changed Password"), HttpStatus.OK);
    }

    @RequestMapping(value = "changeImage", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> changeImage(@Valid @RequestBody AuthChangeImagePathDTO dto) {
        service.changeImagePath(dto);
        return new ResponseEntity<>(new Data<>("Successfully Changed Image"), HttpStatus.OK);
    }


}
