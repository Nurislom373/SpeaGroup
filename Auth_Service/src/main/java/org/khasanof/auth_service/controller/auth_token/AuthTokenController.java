package org.khasanof.auth_service.controller.auth_token;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_token.AuthTokenService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/token/*")
public class AuthTokenController extends AbstractController<AuthTokenService> {

    public AuthTokenController(AuthTokenService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody AuthTokenCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Token"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthTokenGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }
}
