package org.khasanof.auth_service.validator;

import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.exception.exceptions.InvalidValidationException;

import java.io.Serializable;

public abstract class AbstractValidator<CD extends BaseDTO, UD extends GenericDTO, K extends Serializable> implements BaseValidator {

    public abstract void validCreateDTO(CD cd) throws InvalidValidationException;

    public abstract void validUpdateDTO(UD ud) throws InvalidValidationException;

    public abstract void validKey(K k) throws InvalidValidationException;
}
