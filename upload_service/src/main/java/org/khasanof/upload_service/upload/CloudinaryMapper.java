package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.upload.dto.CloudinaryDetailDTO;
import org.khasanof.upload_service.upload.dto.CloudinaryGetDTO;
import org.khasanof.upload_service.upload.entity.CloudinaryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CloudinaryMapper {

    CloudinaryGetDTO getDTO(CloudinaryEntity entity);

    CloudinaryDetailDTO detailDTO(CloudinaryEntity entity);

    List<CloudinaryGetDTO> getListDTO(List<CloudinaryEntity> entities);

}
