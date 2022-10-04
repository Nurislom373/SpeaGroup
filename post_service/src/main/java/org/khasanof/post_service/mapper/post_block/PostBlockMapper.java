package org.khasanof.post_service.mapper.post_block;

import org.khasanof.post_service.dto.post_block.PostBlockCreateDTO;
import org.khasanof.post_service.dto.post_block.PostBlockDetailDTO;
import org.khasanof.post_service.dto.post_block.PostBlockGetDTO;
import org.khasanof.post_service.dto.post_block.PostBlockUpdateDTO;
import org.khasanof.post_service.entity.post_block.PostBlockEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostBlockMapper extends GenericMapper<PostBlockCreateDTO, PostBlockUpdateDTO, PostBlockGetDTO, PostBlockDetailDTO, PostBlockEntity> {
}
