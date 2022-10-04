package org.khasanof.post_service.mapper.category;

import org.khasanof.post_service.dto.category.CategoryCreateDTO;
import org.khasanof.post_service.dto.category.CategoryDetailDTO;
import org.khasanof.post_service.dto.category.CategoryGetDTO;
import org.khasanof.post_service.dto.category.CategoryUpdateDTO;
import org.khasanof.post_service.entity.category.CategoryEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryCreateDTO, CategoryUpdateDTO, CategoryGetDTO, CategoryDetailDTO, CategoryEntity> {
}
