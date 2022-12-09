package org.khasanof.question_service.controller.question_category;

import org.khasanof.question_service.controller.AbstractController;
import org.khasanof.question_service.criteria.question_category.QuestionCategoryCriteria;
import org.khasanof.question_service.dto.question_category.*;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.service.question_category.QuestionCategoryService;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/7/2022
 * <br/>
 * Time: 11:08 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question_category
 */
@RestController
@RequestMapping(value = BaseUtils.PATH + "/question_category/*")
public class QuestionCategoryController extends AbstractController<QuestionCategoryService> {

    public QuestionCategoryController(QuestionCategoryService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody QuestionCategoryCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Question Category"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "addCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> addCategory(@Valid @RequestBody QuestionCategoryAddDTO dto) {
        service.addCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added Category - Question Category"), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> deleteCategory(@Valid @RequestBody QuestionCategoryDeleteDTO dto) {
        service.deleteCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Deleted Category - Question Category"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionCategoryGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionCategoryDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<QuestionCategoryGetDTO>>> list(@Valid QuestionCategoryCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
