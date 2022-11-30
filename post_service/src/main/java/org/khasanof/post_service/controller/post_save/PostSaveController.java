package org.khasanof.post_service.controller.post_save;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post_save.PostSaveCriteria;
import org.khasanof.post_service.dto.post_save.PostSaveCreateDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDeleteDTO;
import org.khasanof.post_service.dto.post_save.PostSaveDetailDTO;
import org.khasanof.post_service.dto.post_save.PostSaveGetDTO;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_save.PostSaveService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_save/*")
public class PostSaveController extends AbstractController<PostSaveService> {

    public PostSaveController(PostSaveService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostSaveCreateDTO dto) {
        service.addSave(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Post Save"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "deleteSave", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteSave(@RequestBody PostSaveDeleteDTO dto) {
        service.deleteSaved(dto);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Save"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Save"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostSaveGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostSaveDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostSaveGetDTO>>> list(@Valid PostSaveCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
