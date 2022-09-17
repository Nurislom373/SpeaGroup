package org.khasanof.auth_service.criteria.auth_user;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.enums.auth_user.AuthUserFieldsEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class AuthUserCriteria extends GenericCriteria {
    private AuthUserFieldsEnum fieldsEnum;
}
