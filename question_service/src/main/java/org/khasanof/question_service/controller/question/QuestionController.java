package org.khasanof.question_service.controller.question;

import org.khasanof.question_service.controller.AbstractController;
import org.khasanof.question_service.criteria.question.QuestionCriteria;
import org.khasanof.question_service.dto.question.QuestionCreateDTO;
import org.khasanof.question_service.dto.question.QuestionDetailDTO;
import org.khasanof.question_service.dto.question.QuestionGetDTO;
import org.khasanof.question_service.dto.question.QuestionUpdateDTO;
import org.khasanof.question_service.response.Data;
import org.khasanof.question_service.service.question.QuestionService;
import org.khasanof.question_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/6/2022
 * <br/>
 * Time: 9:17 PM
 * <br/>
 * Package: org.khasanof.question_service.controller.question
 */
@RestController
@RequestMapping(value = BaseUtils.PATH + "/question/*")
public class QuestionController extends AbstractController<QuestionService> {

    public QuestionController(QuestionService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody QuestionCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Question"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@Valid @RequestBody QuestionUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Question"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Question"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<QuestionDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<QuestionGetDTO>>> list(@Valid QuestionCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria)), HttpStatus.OK);
    }
}
