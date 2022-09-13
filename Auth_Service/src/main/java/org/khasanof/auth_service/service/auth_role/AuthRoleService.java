package org.khasanof.auth_service.service.auth_role;

import org.khasanof.auth_service.criteria.auth_role.AuthRoleCriteria;
import org.khasanof.auth_service.dto.auth_role.AuthRoleCreateDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleDetailDTO;
import org.khasanof.auth_service.dto.auth_role.AuthRoleGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthRoleService extends GenericGDLService<AuthRoleGetDTO, AuthRoleDetailDTO, String, AuthRoleCriteria>, BaseService {

    void create(AuthRoleCreateDTO dto);

    void delete(String id);

    long count();

}
