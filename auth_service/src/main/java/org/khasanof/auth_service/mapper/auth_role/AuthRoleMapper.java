package org.khasanof.auth_service.mapper.auth_role;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleDetailDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleGetDTO;
import org.khasanof.auth_service.entity.auth_role.AuthRoleEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthRoleMapper extends GenericMapper<AuthRoleCreateDTO, GenericDTO, AuthRoleGetDTO, AuthRoleDetailDTO, AuthRoleEntity> {
}
