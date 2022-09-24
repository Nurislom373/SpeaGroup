package org.khasanof.auth_service.mapper.auth_follower;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerDetailDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerGetDTO;
import org.khasanof.auth_service.entity.auth_follower.AuthFollowerEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthFollowerMapper extends GenericMapper<AuthFollowerCreateDTO, GenericDTO, AuthFollowerGetDTO, AuthFollowerDetailDTO, AuthFollowerEntity> {
}
