package org.khasanof.auth_service.controller.auth_info;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.*;
import org.khasanof.auth_service.dto.category.*;
import org.khasanof.auth_service.dto.location.LocationCreateDTO;
import org.khasanof.auth_service.dto.location.LocationUpdateDTO;
import org.khasanof.auth_service.response.Data;
import org.khasanof.auth_service.service.auth_info.AuthInfoService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth_info/*")
public class AuthInfoController extends AbstractController<AuthInfoService> {

    public AuthInfoController(AuthInfoService service) {
        super(service);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Data<String>> create(@Valid @RequestBody AuthInfoCreateDTO dto) {
        service.create(dto);
        return new ResponseEntity<>(new Data<>("Successfully Created - Info"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> update(@Valid @RequestBody AuthInfoUpdateDTO dto) {
        service.update(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "addLocation", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> addLocation(@Valid @RequestBody LocationCreateDTO dto) {
        service.addLocation(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added Location - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "updateLocation", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> updateLocation(@Valid @RequestBody LocationUpdateDTO dto) {
        service.updateLocation(dto);
        return new ResponseEntity<>(new Data<>("Successfully Updated Location - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteLocation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteLocation(@PathVariable String id) {
        service.deleteLocation(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted Location - Info"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "addCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> addCategory(@Valid @RequestBody CategoryAddDTO dto) {
        service.addCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added Category - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "addAllCategory", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> addCategory(@Valid @RequestBody CategoryAddAllDTO dto) {
        service.addAllCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Added All Category - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "deleteCategory",method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteCategory(@Valid @RequestBody CategoryDeleteDTO dto) {
        service.deleteCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Deleted Category - Info"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "deleteAllCategory",method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> deleteCategory(@Valid @RequestBody CategoryDeleteAllDTO dto) {
        service.deleteAllCategory(dto);
        return new ResponseEntity<>(new Data<>("Successfully Deleted All Category - Info"), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "getCategoryList/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<List<CategoryDetailDTO>>> getCategoryList(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.listCategory(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Data<String>> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(new Data<>("Successfully Deleted - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "changeVisibility", method = RequestMethod.PUT)
    public ResponseEntity<Data<String>> changeVisibility(@RequestBody AuthInfoChangeVisibilityDTO dto) {
        service.changeVisibility(dto);
        return new ResponseEntity<>(new Data<>("Successfully Status Changed - Info"), HttpStatus.OK);
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthInfoGetDTO>> get(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<Data<AuthInfoDetailDTO>> detail(@PathVariable String id) {
        return new ResponseEntity<>(new Data<>(service.detail(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthInfoGetDTO>>> list(@Valid AuthInfoCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.list(criteria), service.count()), HttpStatus.OK);
    }

    @RequestMapping(value = "list/search", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthInfoGetDTO>>> listSearch(@Valid AuthInfoSearchCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.listWithSc(criteria), service.count()), HttpStatus.OK);
    }

    @RequestMapping(value = "list/between", method = RequestMethod.GET)
    public ResponseEntity<Data<List<AuthInfoGetDTO>>> listBetween(@Valid AuthInfoBetweenCriteria criteria) {
        return new ResponseEntity<>(new Data<>(service.listWithBc(criteria), service.count()), HttpStatus.OK);
    }

}
