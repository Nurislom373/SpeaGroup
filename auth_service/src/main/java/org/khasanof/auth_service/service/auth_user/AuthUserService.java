package org.khasanof.auth_service.service.auth_user;

import org.khasanof.auth_service.criteria.auth_user.AuthUserBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserCriteria;
import org.khasanof.auth_service.criteria.auth_user.AuthUserSearchCriteria;
import org.khasanof.auth_service.dto.auth_user.AuthUserCreateDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserDetailDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserGetDTO;
import org.khasanof.auth_service.dto.auth_user.AuthUserUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;
import org.khasanof.auth_service.service.GenericGDLService;
import org.khasanof.auth_service.service.GenericUtilService;

import java.util.List;

public interface AuthUserService extends
        GenericCUDService<AuthUserCreateDTO, AuthUserUpdateDTO, String>,
        GenericGDLService<AuthUserGetDTO, AuthUserDetailDTO, String, AuthUserCriteria>,
        GenericUtilService<AuthUserGetDTO, AuthUserSearchCriteria, AuthUserBetweenCriteria>, BaseService {

    boolean exist(String id);

}
