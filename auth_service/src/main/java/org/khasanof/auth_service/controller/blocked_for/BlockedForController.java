package org.khasanof.auth_service.controller.blocked_for;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.blocked_for.BlockedForService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/blocked_for/*")
public class BlockedForController extends AbstractController<BlockedForService> {

    public BlockedForController(BlockedForService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody BlockedForCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Blocked For"), HttpStatus.CREATED);
    }
}
