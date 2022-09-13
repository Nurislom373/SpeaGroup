package org.khasanof.auth_service.controller.auth_token;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.auth_role.AuthRoleCriteria;
import org.khasanof.auth_service.criteria.auth_token.AuthTokenCriteria;
import org.khasanof.auth_service.dto.auth_role.AuthRoleGetDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_token.AuthTokenService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@RequestBody AuthTokenUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Token"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Token"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthTokenGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthTokenDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthTokenGetDTO>>> list(@Valid AuthTokenCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }

}
