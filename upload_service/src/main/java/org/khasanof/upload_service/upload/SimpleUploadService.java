package org.khasanof.upload_service.upload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.khasanof.upload_service.upload.dto.CloudinaryDetailDTO;
import org.khasanof.upload_service.upload.dto.CloudinaryGetDTO;
import org.khasanof.upload_service.upload.entity.CloudinaryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SimpleUploadService implements UploadService {

    private final CloudinaryRepository cloudinaryRepository;
    private final WriteLocalFileService localFileService;
    private final Cloudinary cloudinary;

    public SimpleUploadService(CloudinaryRepository couldinaryRepository, WriteLocalFileService localFileService) {
        this.cloudinaryRepository = couldinaryRepository;
        this.localFileService = localFileService;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dtkrdcbhi",
                "api_key", "723978565627878",
                "api_secret", "8j21viAfIolIivwVxeum9endXYg"));
    }

    @Override
    public CloudinaryGetDTO upload(MultipartFile file) {
        Assert.notNull(file, "file must be not null required!");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();

            var public_id = cloudinary.uploader().upload(
                    new File(localFileService.writeFile(file)),
                    ObjectUtils.asMap("public_id",
                            file.getOriginalFilename()));

            for (Object o : public_id.keySet()) {
                if (!String.valueOf(o).equals("created_at"))
                    objectNode.put(String.valueOf(o), String.valueOf(public_id.get(o.toString())));
            }

            CloudinaryEntity entity = objectMapper.readValue(objectNode.toString().getBytes(), CloudinaryEntity.class);
            CloudinaryEntity cloudinaryEntity = cloudinaryRepository.save(entity);
            CloudinaryGetDTO getDTO = new CloudinaryGetDTO();
            BeanUtils.copyProperties(cloudinaryEntity, getDTO);
            return getDTO;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CloudinaryGetDTO> multiUpload(MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::upload)
                .toList();
    }

    @Override
    public CloudinaryGetDTO get(String id) {
        return null;
    }

    @Override
    public CloudinaryDetailDTO detail(String id) {
        return null;
    }

    @Override
    public List<CloudinaryGetDTO> getMultiGet(List<String> ids) {
        return null;
    }

    @Override
    public List<CloudinaryGetDTO> list(UploadCriteria criteria) {
        return null;
    }

}
