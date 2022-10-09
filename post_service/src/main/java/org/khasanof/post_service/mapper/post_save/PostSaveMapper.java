package org.khasanof.post_service.mapper.post_save;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_save.PostSaveCreateDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDetailDTO;
import org.khasanof.post_service.dto.post_save.PostSaveGetDTO;
import org.khasanof.post_service.entity.post_save.PostSaveEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostSaveMapper extends GenericMapper<PostSaveCreateDTO, GenericDTO, PostSaveGetDTO, PostSaveDetailDTO, PostSaveEntity> {
}
