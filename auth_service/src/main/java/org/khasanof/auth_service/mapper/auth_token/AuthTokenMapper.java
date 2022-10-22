package org.khasanof.auth_service.mapper.auth_token;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenCreateDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenDetailDTO;
import org.khasanof.auth_service.dto.auth_token.AuthTokenGetDTO;
import org.khasanof.auth_service.entity.auth_token.AuthTokenEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthTokenMapper extends GenericMapper<
        AuthTokenCreateDTO,
        GenericDTO,
        AuthTokenGetDTO,
        AuthTokenDetailDTO,
        AuthTokenEntity> {
}
