package org.khasanof.post_service.mapper.post_rating;

import org.khasanof.post_service.dto.GenericDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingCreateDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingDetailDTO;
import org.khasanof.post_service.dto.post_rating.PostRatingGetDTO;
import org.khasanof.post_service.entity.post_rating.PostRatingEntity;
import org.khasanof.post_service.mapper.GenericMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostRatingMapper extends GenericMapper<PostRatingCreateDTO, GenericDTO, PostRatingGetDTO, PostRatingDetailDTO, PostRatingEntity> {
}
