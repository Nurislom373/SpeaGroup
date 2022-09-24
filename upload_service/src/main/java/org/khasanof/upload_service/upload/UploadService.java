package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.upload.dto.UploadDetailDTO;
import org.khasanof.upload_service.upload.dto.UploadGetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    void upload(MultipartFile file);

    void multiUpload(MultipartFile[] files);

    UploadGetDTO get(String id);

    UploadDetailDTO detail(String id);

    List<UploadGetDTO> getMultiGet(List<String> ids);

    List<UploadGetDTO> list(UploadCriteria criteria);

}
