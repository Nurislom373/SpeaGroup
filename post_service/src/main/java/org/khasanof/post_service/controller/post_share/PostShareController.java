package org.khasanof.post_service.controller.post_share;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post_share.PostShareCriteria;
import org.khasanof.post_service.dto.post_share.PostShareCreateDTO;
import org.khasanof.post_service.dto.post_share.PostShareDetailDTO;
import org.khasanof.post_service.dto.post_share.PostShareGetDTO;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_share.PostShareService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_share/*")
public class PostShareController extends AbstractController<PostShareService> {

    public PostShareController(PostShareService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostShareCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Post Share"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Share"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostShareGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostShareDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostShareGetDTO>>> list(@Valid PostShareCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
