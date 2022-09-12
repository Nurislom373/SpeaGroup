package org.khasanof.auth_service.controller.auth;

import io.swagger.v3.oas.annotations.Parameter;
import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth.AuthUserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthUserController extends AbstractController<AuthUserServiceImpl> {
    public AuthUserController(AuthUserServiceImpl service) {
        super(service);
    }


    @RequestMapping(value = PATH + "/authUser/create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody AuthUserCreateDTO dto) {
        return service.create(dto);
    }


    @RequestMapping(value = PATH + "/authUser/update", method = RequestMethod.PUT)
    public ResponseEntity<Data<AuthUserDTO>> update(@RequestBody AuthUserUpdateDTO dto) {
        return service.update(dto);
    }


    @RequestMapping(value = PATH + "/authUser/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<Void>> delete(@Parameter(description = "id of authUser to be deleted",required = true) @PathVariable String id) {
        return service.delete(id);
    }


    @RequestMapping(value = PATH + "/authUser/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthUserDTO>> get(@Parameter(description = "id of authUser to be gotten",required = true)@PathVariable String id) {
        return service.get(id);
    }

    @RequestMapping(value = PATH + "/authUser/getAll", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserDTO>>> getAll(@RequestBody GenericCriteria criteria) {
        return service.getAll(criteria);
    }


    @RequestMapping(value = PATH + "/authUser/blocked", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthUserDTO>>> getAllBlocked(@RequestBody GenericCriteria criteria) {
        return service.getAllBlocked(criteria);
    }


    @RequestMapping(value = PATH + "/authUser/block/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Data<Void>> block(@Parameter(description = "id of authUser to be blocked",required = true)@PathVariable String id) {
        return service.block(id);
    }


    @RequestMapping(value = PATH + "/authUser/unblock/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Data<Void>> unblock(@Parameter(description = "id of authUser to be unblocked",required = true)@PathVariable String id) {
        return service.unblock(id);
    }




}
