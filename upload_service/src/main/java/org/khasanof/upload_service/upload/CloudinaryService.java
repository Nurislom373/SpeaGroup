package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.upload.dto.CloudinaryDetailDTO;
import org.khasanof.upload_service.upload.dto.CloudinaryGetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudinaryService {

    CloudinaryGetDTO upload(MultipartFile file);

    List<CloudinaryGetDTO> multiUpload(MultipartFile[] files);

    CloudinaryGetDTO get(String id);

    CloudinaryDetailDTO detail(String id);

    List<CloudinaryGetDTO> getMultiGet(List<String> ids);

    List<CloudinaryGetDTO> list(CloudinaryCriteria criteria);

}
