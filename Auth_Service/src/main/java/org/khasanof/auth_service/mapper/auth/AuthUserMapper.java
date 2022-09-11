package org.khasanof.auth_service.mapper.auth;


import org.khasanof.auth_service.dto.auth.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth.AuthUserDTO;
import org.khasanof.auth_service.dto.auth.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.BaseMapper;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends GenericMapper<AuthUserEntity, AuthUserDTO, AuthUserCreateDTO, AuthUserUpdateDTO> {
    @Override
    AuthUserDTO toDto(AuthUserEntity authUserEntity);

    @Override
    List<AuthUserDTO> toDto(List<AuthUserEntity> e);

    @Override
    AuthUserEntity fromCreateDto(AuthUserCreateDTO authUserCreateDTO);

    @Override
    AuthUserEntity fromUpdateDto(AuthUserUpdateDTO d);
}
