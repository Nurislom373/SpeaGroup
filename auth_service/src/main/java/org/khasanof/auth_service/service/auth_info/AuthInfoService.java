package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.*;
import org.khasanof.auth_service.entity.auth_info.AuthInfoEntity;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;
import org.khasanof.auth_service.service.GenericGDLService;
import org.khasanof.auth_service.service.GenericUtilService;

public interface AuthInfoService extends
        GenericCUDService<AuthInfoCreateDTO, AuthInfoUpdateDTO, String>,
        GenericGDLService<AuthInfoGetDTO, AuthInfoDetailDTO, String, AuthInfoCriteria>,
        GenericUtilService<AuthInfoGetDTO, AuthInfoSearchCriteria, AuthInfoBetweenCriteria>,
        CategoryADGLCService,
        LocationCUDService,
        BaseService {

    AuthInfoEntity getByUserId(String id);

    void changeVisibility(AuthInfoChangeVisibilityDTO dto);

    void deleteByUserId(String id);

}
