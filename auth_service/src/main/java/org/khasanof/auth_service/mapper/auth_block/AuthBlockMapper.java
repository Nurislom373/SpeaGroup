package org.khasanof.auth_service.mapper.auth_block;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockDetailDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockGetDTO;
import org.khasanof.auth_service.entity.auth_block.AuthBlockEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AuthBlockMapper extends GenericMapper<AuthBlockCreateDTO, GenericDTO, AuthBlockGetDTO, AuthBlockDetailDTO, AuthBlockEntity> {
}
