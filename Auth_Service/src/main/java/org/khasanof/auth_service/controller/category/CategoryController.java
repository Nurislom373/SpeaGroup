package org.khasanof.auth_service.controller.category;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.category.CategoryCriteria;
import org.khasanof.auth_service.dto.category.CategoryCreateDTO;
import org.khasanof.auth_service.dto.category.CategoryGetDTO;
import org.khasanof.auth_service.dto.category.CategoryUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.category.CategoryService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/category/*")
public class CategoryController extends AbstractController<CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@RequestBody CategoryCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Category"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@RequestBody CategoryUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Category"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Category"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<CategoryGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<CategoryGetDTO>>> list(@Valid CategoryCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria), service.count()), HttpStatus.OK);
    }

}
