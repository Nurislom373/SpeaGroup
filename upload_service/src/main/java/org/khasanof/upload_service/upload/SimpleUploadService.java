package org.khasanof.upload_service.upload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.khasanof.upload_service.upload.dto.UploadDetailDTO;
import org.khasanof.upload_service.upload.dto.UploadGetDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SimpleUploadService implements UploadService {

    private final UploadRepository repository;
    private final WriteLocalFileService localFileService;
    private final Cloudinary cloudinary;

    public SimpleUploadService(UploadRepository repository, WriteLocalFileService localFileService) {
        this.repository = repository;
        this.localFileService = localFileService;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtkrdcbhi",
                "api_key", "723978565627878",
                "api_secret", "8j21viAfIolIivwVxeum9endXYg"));
    }

    @Override
    public void upload(MultipartFile file) {
        Assert.notNull(file, "file must be not null required!");
        try {
            var public_id = cloudinary.uploader().upload(
                    new File(localFileService.writeFile(file)),
                    ObjectUtils.asMap("public_id",
                            file.getOriginalFilename()));
            System.out.println("public_id = " + public_id);
//            repository.save(UploadEntity.builder()
//                    .url());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void multiUpload(MultipartFile[] files) {

    }

    @Override
    public UploadGetDTO get(String id) {
        return null;
    }

    @Override
    public UploadDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<UploadGetDTO> getMultiGet(List<String> ids) {
        return null;
    }

    @Override
    public List<UploadGetDTO> list(UploadCriteria criteria) {
        return null;
    }

}
