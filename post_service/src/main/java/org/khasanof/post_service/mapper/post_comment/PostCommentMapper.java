package org.khasanof.post_service.mapper.post_comment;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentCreateDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentDetailDTO;
import org.khasanof.post_service.dto.post_comment.PostCommentGetDTO;
import org.khasanof.post_service.entity.post_comment.PostCommentEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostCommentMapper extends GenericMapper<PostCommentCreateDTO, GenericDTO, PostCommentGetDTO, PostCommentDetailDTO, PostCommentEntity> {
}
