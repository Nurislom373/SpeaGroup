package org.khasanof.auth_service.controller.employment;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.employment.EmploymentCreateDTO;
import org.khasanof.auth_service.dto.employment.EmploymentGetDTO;
import org.khasanof.auth_service.dto.employment.EmploymentUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.employment.EmploymentService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/employment/*")
public class EmploymentController extends AbstractController<EmploymentService> {

    public EmploymentController(EmploymentService service) {
        super(service);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> add(@Valid @RequestBody EmploymentCreateDTO dto) {
        service.add(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> addAll(@Valid @RequestBody List<EmploymentCreateDTO> dtos) {
        service.addAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Added - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@Valid @RequestBody EmploymentUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "updateAll", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> updateAll(@Valid @RequestBody List<EmploymentUpdateDTO> dtos) {
        service.updateAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/infoId={infoId}&id={id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String infoId, @PathVariable String id) {
        service.delete(infoId, id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Employment"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "get/infoId={infoId}&id={id}", method = RequestMethod.GET)
    public ResponseEntity<Data<EmploymentGetDTO>> get(@PathVariable String infoId, @PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.getEmployment(infoId, id)), HttpStatus.OK);
    }

    @RequestMapping(value = "getAll/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<EmploymentGetDTO>>> getAll(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.listEmployments(id), service.countEmployment(id)), HttpStatus.OK);
    }
}
