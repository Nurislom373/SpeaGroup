package org.khasanof.auth_service.controller.auth_follower;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.auth_follower.AuthFollowerCriteria;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerDetailDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerGetDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_follower.AuthFollowerService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth_follower/*")
public class AuthFollowerController extends AbstractController<AuthFollowerService> {

    public AuthFollowerController(AuthFollowerService service) {
        super(service);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> add(@Valid @RequestBody AuthFollowerCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Auth Follower"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Auth Follower"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthFollowerGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthFollowerDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthFollowerGetDTO>>> list(@Valid @NotNull AuthFollowerCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }


}
