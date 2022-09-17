package org.khasanof.auth_service.mapper.employment;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.entity.employment.EmploymentEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EmploymentMapper extends GenericMapper<EmploymentCreateDTO, EmploymentUpdateDTO, EmploymentGetDTO, GenericDTO, EmploymentEntity> {
}
