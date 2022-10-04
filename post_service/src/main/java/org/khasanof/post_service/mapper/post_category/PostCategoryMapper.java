package org.khasanof.post_service.mapper.post_category;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryDetailDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryGetDTO;
import org.khasanof.post_service.entity.post_category.PostCategoryEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostCategoryMapper extends GenericMapper<PostCategoryAddDTO, GenericDTO, PostCategoryGetDTO, PostCategoryDetailDTO, PostCategoryEntity> {
}
