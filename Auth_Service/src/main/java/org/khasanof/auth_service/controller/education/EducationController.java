package org.khasanof.auth_service.controller.education;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationGetDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.education.EducationService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/education/*")
public class EducationController extends AbstractController<EducationService> {

    public EducationController(EducationService service) {
        super(service);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> add(@RequestBody EducationCreateDTO dto) {
        service.add(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added - Education"), HttpStatus.OK);
    }

    @RequestMapping(value = "addAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> addAll(@RequestBody List<EducationCreateDTO> dtos) {
        service.addAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Added - Education"), HttpStatus.OK);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> update(@RequestBody EducationUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Education"), HttpStatus.OK);
    }

    @RequestMapping(value = "updateAll", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> updateAll(@RequestBody List<EducationUpdateDTO> dtos) {
        service.updateAll(dtos);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Education"), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@RequestParam String infoId,
                                               @RequestParam String id) {
        service.delete(infoId, id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Education"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/", method = RequestMethod.GET)
    public ResponseEntity<Data<EducationGetDTO>> get(@RequestParam String infoId,
                                                     @RequestParam String id) {
        return new ResponseEntity<>(new Data<>(service.getEducation(infoId, id)), HttpStatus.OK);
    }

    @RequestMapping(value = "getAll/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<EducationGetDTO>>> getAll(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.listEducations(id), service.countEducation(id)), HttpStatus.OK);
    }
}
