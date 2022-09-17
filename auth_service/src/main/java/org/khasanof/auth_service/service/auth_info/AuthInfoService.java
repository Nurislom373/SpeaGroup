package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoBetweenCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoDetailDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoGetDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;
import org.khasanof.auth_service.service.GenericGDLService;
import org.khasanof.auth_service.service.GenericUtilService;

public interface AuthInfoService extends
        GenericCUDService<AuthInfoCreateDTO, AuthInfoUpdateDTO, String>,
        GenericGDLService<AuthInfoGetDTO, AuthInfoDetailDTO, String, AuthInfoCriteria>,
        GenericUtilService<AuthInfoGetDTO, AuthInfoSearchCriteria, AuthInfoBetweenCriteria>,
        LocationCUDService,
        BaseService {
}
