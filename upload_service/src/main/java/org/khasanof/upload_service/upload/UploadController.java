package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.response.Data;
import org.khasanof.upload_service.upload.dto.CloudinaryGetDTO;
import org.khasanof.upload_service.upload.entity.CloudinaryEntity;
import org.khasanof.upload_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/upload/*")
public class UploadController {

    private final UploadService service;

    public UploadController(UploadService service) {
        this.service = service;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Data<CloudinaryGetDTO>> add(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(new Data<>(service.upload(file)), HttpStatus.OK);
    }

}
