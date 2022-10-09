package org.khasanof.post_service.mapper.post;

import org.khasanof.post_service.dto.post.*;
import org.khasanof.post_service.entity.post.PostEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<PostCreateDTO, PostUpdateDTO, PostGetDTO, PostDetailDTO, PostEntity> {

    PostDetWComDTO fromPostDetWComDTO(PostEntity entity);

}
