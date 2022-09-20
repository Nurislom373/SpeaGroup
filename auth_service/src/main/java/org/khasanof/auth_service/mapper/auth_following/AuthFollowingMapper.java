package org.khasanof.auth_service.mapper.auth_following;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerDetailDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerGetDTO;
import org.khasanof.auth_service.entity.auth_following.AuthFollowingEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthFollowingMapper extends GenericMapper<AuthFollowerCreateDTO, GenericDTO, AuthFollowerGetDTO, AuthFollowerDetailDTO, AuthFollowingEntity> {
}
