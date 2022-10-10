package org.khasanof.post_service.mapper.post_share;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.entity.post_share.PostShareEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostShareMapper extends GenericMapper<PostShareCreateDTO, GenericDTO, PostShareGetDTO, PostShareDetailDTO, PostShareEntity> {
}
