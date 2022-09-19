package org.khasanof.auth_service.controller.auth_user;

import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.repository.auth_block.AuthBlockRepository;
import org.khasanof.auth_service.response.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthBlockController {
    @Autowired
    private AuthBlockRepository repository;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthBlockEntity>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(repository.findById(id).get()), HttpStatus.OK);
    }

}
