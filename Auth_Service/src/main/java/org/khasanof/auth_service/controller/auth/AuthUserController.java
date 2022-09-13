package org.khasanof.auth_service.controller.auth;

import io.swagger.v3.oas.annotations.Parameter;
import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_user.AuthUserService;
import org.khasanof.auth_service.service.auth_user.AuthUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthUserController extends AbstractController<AuthUserService> {
    public AuthUserController(AuthUserService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/authUser/create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody AuthUserCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - User"), HttpStatus.CREATED);
    }


    @RequestMapping(value = PATH + "/authUser/update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@RequestBody AuthUserUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - User"), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/authUser/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@Parameter(description = "id of authUser to be deleted", required = true) @PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - User"), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/authUser/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthUserGetDTO>> get(@Parameter(description = "id of authUser to be gotten", required = true) @PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/authUser/getAll", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserGetDTO>>> getAll(@RequestBody AuthUserCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/authUser/blocked", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserGetDTO>>> getAllBlocked(@RequestBody AuthUserCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.getAllBlocked(criteria)), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/authUser/block/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Data<String>> block(@Parameter(description = "id of authUser to be blocked", required = true) @PathVariable String id) {
        service.block(id);
        return new ResponseEntity<>(new Data<>("Successfully Block - User"), HttpStatus.OK);
    }


    @RequestMapping(value = PATH + "/authUser/unblock/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Data<String>> unblock(@Parameter(description = "id of authUser to be unblocked", required = true) @PathVariable String id) {
        service.unblock(id);
        return new ResponseEntity<>(new Data<>("Successfully Unblock - User"), HttpStatus.OK);
    }


}
