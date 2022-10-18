package org.khasanof.auth_service.controller.auth_invite;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.auth_invite.AuthInviteCriteria;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteChangeStatusDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteDetailDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteGetDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_invite.AuthInviteService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth_invite/*")
public class AuthInviteController extends AbstractController<AuthInviteService> {

    public AuthInviteController(AuthInviteService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody AuthInviteCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Auth Invite"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Auth Invite"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "deleteInvite/id={id}&inviteUserId={inviteUserId}", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> deleteInvite(@PathVariable String id, @PathVariable String inviteUserId) {
        service.deleteRequest(id, inviteUserId);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Auth Invite"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "inviteChangeStatus", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> inviteChangeStatus(@RequestBody AuthInviteChangeStatusDTO dto) {
        service.inviteSelect(dto);
        return new ResponseEntity<>(new Data<>("Successfully Status Changed - Auth Invite"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthInviteGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthInviteDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthInviteGetDTO>>> list(@Valid AuthInviteCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }


}
