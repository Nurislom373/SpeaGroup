package org.khasanof.auth_service.controller.auth_user;

import io.swagger.v3.oas.annotations.Parameter;
import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.auth_user.AuthUserBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth_user/*")
public class AuthUserController extends AbstractController<AuthUserService> {
    public AuthUserController(AuthUserService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody AuthUserCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - User"), HttpStatus.CREATED);
    }


    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@RequestBody AuthUserUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - User"), HttpStatus.OK);
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@Parameter(description = "id of authUser to be deleted", required = true) @PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - User"), HttpStatus.OK);
    }


    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthUserGetDTO>> get(@Parameter(description = "id of authUser to be gotten", required = true) @PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserGetDTO>>> list(@Valid AuthUserCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria), service.count()), HttpStatus.OK);
    }

    @RequestMapping(value = "list/search/", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserGetDTO>>> list(@RequestParam String key,
                                                           @RequestParam String operation,
                                                           @RequestParam String value) {
        return new ResponseEntity<>(new Data<>(service.listWithSc(new AuthUserSearchCriteria(key, operation, value)), service.count()), HttpStatus.OK);
    }

    @RequestMapping(value = "list/between/", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserGetDTO>>> list(@RequestParam String key,
                                                           @RequestParam Integer from,
                                                           @RequestParam Integer to) {
        return new ResponseEntity<>(new Data<>(service.listWithBc(new AuthUserBetweenCriteria(key, from, to)), service.count()), HttpStatus.OK);
    }


}
