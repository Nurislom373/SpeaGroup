package org.khasanof.post_service.mapper.post_view;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_view.PostViewCreateDTO;
import org.khasanof.post_service.dto.post_view.PostViewDetailDTO;
import org.khasanof.post_service.dto.post_view.PostViewGetDTO;
import org.khasanof.post_service.entity.post_view.PostViewEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostViewMapper extends GenericMapper<PostViewCreateDTO, GenericDTO, PostViewGetDTO, PostViewDetailDTO, PostViewEntity> {
}
