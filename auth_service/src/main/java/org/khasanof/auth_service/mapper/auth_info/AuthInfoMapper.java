package org.khasanof.auth_service.mapper.auth_info;

import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoDetailDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoGetDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthInfoMapper extends GenericMapper<AuthInfoCreateDTO, AuthInfoUpdateDTO, AuthInfoGetDTO, AuthInfoDetailDTO, AuthInfoEntity> {
    @Override
    @Mapping(target = "user", source = "entity.userId")
    AuthInfoDetailDTO fromDetailDTO(AuthInfoEntity entity);
}
