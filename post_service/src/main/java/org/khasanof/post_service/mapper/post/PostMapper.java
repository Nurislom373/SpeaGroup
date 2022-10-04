package org.khasanof.post_service.mapper.post;

import org.khasanof.post_service.dto.post.PostCreateDTO;
import org.khasanof.post_service.dto.post.PostDetailDTO;
import org.khasanof.post_service.dto.post.PostGetDTO;
import org.khasanof.post_service.dto.post.PostUpdateDTO;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<PostCreateDTO, PostUpdateDTO, PostGetDTO, PostDetailDTO, PostEntity> {
}
