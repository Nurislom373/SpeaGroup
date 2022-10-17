package org.khasanof.auth_service.mapper.auth_invite;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteCreateDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteDetailDTO;
import org.khasanof.auth_service.dto.auth_invite.AuthInviteGetDTO;
import org.khasanof.auth_service.entity.auth_invite.AuthInviteEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthInviteMapper extends GenericMapper<AuthInviteCreateDTO, GenericDTO, AuthInviteGetDTO, AuthInviteDetailDTO, AuthInviteEntity> {
}
