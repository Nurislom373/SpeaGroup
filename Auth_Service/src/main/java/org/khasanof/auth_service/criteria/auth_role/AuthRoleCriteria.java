package org.khasanof.auth_service.criteria.auth_role;

import lombok.Getter;
import lombok.Setter;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.enums.auth_role.AuthRoleFieldsEnum;
import org.springdoc.api.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class AuthRoleCriteria extends GenericCriteria {
    private AuthRoleFieldsEnum fieldsEnum;
}
