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

import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/employment/*")
public class EmploymentController extends AbstractController<EmploymentService> {

    public EmploymentController(EmploymentService service) {
        super(service);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> add(@RequestBody EmploymentCreateDTO dto) {
        service.add(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> addAll(@RequestBody List<EmploymentCreateDTO> dtos) {
        service.addAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Added - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> update(@RequestBody EmploymentUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "updateAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> updateAll(@RequestBody List<EmploymentUpdateDTO> dtos) {
        service.updateAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@RequestParam String infoId,
                                               @RequestParam String id) {
        service.delete(infoId, id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Employment"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/", method = RequestMethod.GET)
    public ResponseEntity<Data<EmploymentGetDTO>> get(@RequestParam String infoId,
                                                      @RequestParam String id) {
        return new ResponseEntity<>(new Data<>(service.getEmployment(infoId, id)), HttpStatus.OK);
    }

    @RequestMapping(value = "getAll/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<EmploymentGetDTO>>> getAll(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.listEmployments(id), service.countEmployment(id)), HttpStatus.OK);
    }
}