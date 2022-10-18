package org.khasanof.auth_service.service.auth_invite;

import org.khasanof.auth_service.criteria.auth_invite.AuthInviteCriteria;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteChangeStatusDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteDetailDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthInviteService extends GenericGDLService<AuthInviteGetDTO, AuthInviteDetailDTO, String, AuthInviteCriteria>, BaseService {

    void create(AuthInviteCreateDTO dto);

    void delete(String id);

    void inviteSelect(AuthInviteChangeStatusDTO dto);

    void deleteRequest(String id, String userId);
    
}
