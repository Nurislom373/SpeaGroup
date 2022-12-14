package org.khasanof.auth_service.criteria.auth_token;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.enums.auth_token.AuthTokenEnum;
import org.khasanof.auth_service.enums.auth_token.AuthTokenFieldsEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class AuthTokenCriteria extends GenericCriteria {
    private AuthTokenFieldsEnum fieldsEnum;
}
