package org.khasanof.question_service.service;

import org.khasanof.post_service.dto.BaseDTO;
import org.khasanof.post_service.dto.GenericDTO;

import java.io.Serializable;

public interface GenericCUDService<CD extends BaseDTO, UD extends GenericDTO, K extends Serializable> {

    void create(CD dto);

    void update(UD dto);

    void delete(K id);
}
