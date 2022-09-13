package org.khasanof.auth_service.mapper.auth_user;


import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDetailDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.entity.auth_user.AuthUserEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends GenericMapper<AuthUserCreateDTO, AuthUserUpdateDTO, AuthUserGetDTO, AuthUserDetailDTO, AuthUserEntity> {
}
