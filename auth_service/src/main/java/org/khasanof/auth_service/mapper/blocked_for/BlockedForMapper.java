package org.khasanof.auth_service.mapper.blocked_for;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForGetDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
import org.khasanof.auth_service.entity.blocked_for.BlockedForEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface BlockedForMapper extends GenericMapper<BlockedForCreateDTO, BlockedForUpdateDTO, BlockedForGetDTO, GenericDTO, BlockedForEntity> {
}
