package org.khasanof.post_service.mapper.post_like;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_like.PostLikeCreateDTO;
import org.khasanof.post_service.dto.post_like.PostLikeDetailDTO;
import org.khasanof.post_service.dto.post_like.PostLikeGetDTO;
import org.khasanof.post_service.entity.post_like.PostLikeEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostLikeMapper extends GenericMapper<PostLikeCreateDTO, GenericDTO, PostLikeGetDTO, PostLikeDetailDTO, PostLikeEntity> {
}
