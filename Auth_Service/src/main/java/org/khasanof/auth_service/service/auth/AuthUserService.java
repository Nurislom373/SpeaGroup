package org.khasanof.auth_service.service.auth;

import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.service.GenericCrudService;

public interface AuthUserService extends GenericCrudService<AuthUserCreateDTO, AuthUserDTO, AuthUserUpdateDTO, GenericCriteria, String> {
}
