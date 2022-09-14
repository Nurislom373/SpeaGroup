package org.khasanof.auth_service.mapper.education;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationGetDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.entity.education.EducationEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface EducationMapper extends GenericMapper<EducationCreateDTO, EducationUpdateDTO, EducationGetDTO, GenericDTO, EducationEntity> {
}
