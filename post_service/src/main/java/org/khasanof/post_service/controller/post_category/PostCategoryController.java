package org.khasanof.post_service.controller.post_category;

import org.khasanof.post_service.controller.AbstractController;
import org.khasanof.post_service.criteria.post_category.PostCategoryCriteria;
import org.khasanof.post_service.dto.post_category.PostCategoryAddAllDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryAddDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryDetailDTO;
import org.khasanof.post_service.dto.post_category.PostCategoryGetDTO;
import org.khasanof.post_service.response.Data;
import org.khasanof.post_service.service.post_category.PostCategoryService;
import org.khasanof.post_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/post_category/*")
public class PostCategoryController extends AbstractController<PostCategoryService> {

    public PostCategoryController(PostCategoryService service) {
        super(service);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostCategoryAddDTO dto) {
        service.addCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Post Category"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody PostCategoryAddAllDTO dto) {
        service.addAllCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully All Added  - Post Category"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Category"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "deleteCategory/id={id}&categoryId={categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteCategory(@PathVariable String id, @PathVariable String categoryId) {
        service.deleteCategory(id, categoryId);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Post Category"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostCategoryGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<PostCategoryDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<PostCategoryGetDTO>>> list(@Valid PostCategoryCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
