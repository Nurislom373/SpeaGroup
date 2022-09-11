package org.khasanof.auth_service.service;

import org.khasanof.auth_service.criteria.BaseCriteria;
import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.response.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericCrudService<
        CD extends BaseDTO,
        D extends BaseDTO,
        UD extends GenericDTO,
        C extends BaseCriteria,
        K extends Serializable
        > extends BaseService {

    ResponseEntity<Data<K>> create(CD createDto);

    ResponseEntity<Data<D>> update(UD updateDto);

    ResponseEntity<Data<Void>> delete(K id);

    ResponseEntity<Data<List<D>>> getAll(C criteria);

    ResponseEntity<Data<D>> get(K id);
}
