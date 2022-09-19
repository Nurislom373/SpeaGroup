package org.khasanof.auth_service.service;

import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.dto.GenericDTO;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;

public interface GenericCUDService<CD extends BaseDTO, UD extends GenericDTO, K extends Serializable> {

    void create(CD dto) throws AlreadyBoundException;

    void update(UD dto);

    void delete(K id);
}
