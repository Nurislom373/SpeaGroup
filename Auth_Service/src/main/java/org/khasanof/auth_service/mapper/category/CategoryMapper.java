package org.khasanof.auth_service.mapper.category;

import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.dto.category.CategoryCreateDTO;
import org.khasanof.auth_service.dto.category.CategoryGetDTO;
import org.khasanof.auth_service.dto.category.CategoryUpdateDTO;
import org.khasanof.auth_service.entity.category.CategoryEntity;
import org.khasanof.auth_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryCreateDTO, CategoryUpdateDTO, CategoryGetDTO, GenericDTO, CategoryEntity> {
}
