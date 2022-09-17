package org.khasanof.auth_service.criteria.auth_info;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.enums.auth_info.AuthInfoFieldsEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class AuthInfoCriteria extends GenericCriteria {
    private AuthInfoFieldsEnum fieldsEnum;
}
