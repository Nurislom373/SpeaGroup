package org.khasanof.upload_service.upload;

import org.khasanof.upload_service.response.Data;
import org.khasanof.upload_service.upload.dto.CloudinaryDetailDTO;
import org.khasanof.upload_service.upload.dto.CloudinaryGetDTO;
import org.khasanof.upload_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/upload/*")
public class CloudinaryController {

    private final CloudinaryService service;

    public CloudinaryController(CloudinaryService service) {
        this.service = service;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Data<CloudinaryGetDTO>> add(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(new Data<>(service.upload(file)), HttpStatus.OK);
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Data<List<CloudinaryGetDTO>>> addAll(@RequestParam("file") MultipartFile[] files) {
        return new ResponseEntity<>(new Data<>(service.multiUpload(files)), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Cloudinary"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "urlCheck/{url}", method = RequestMethod.GET)
    public ResponseEntity<Data<String>> checkUrl(@PathVariable String url) {
        service.checkSecureUrl(url);
        return new ResponseEntity<>(new Data<>("Successfully Check Url - Cloudinary"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<CloudinaryGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<CloudinaryDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<CloudinaryGetDTO>>> list(@Valid CloudinaryCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }

}
