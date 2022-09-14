package org.khasanof.auth_service.service.auth_info;

import org.khasanof.auth_service.criteria.auth_info.AuthInfoCriteria;
import org.khasanof.auth_service.criteria.auth_info.AuthInfoSearchCriteria;
import org.khasanof.auth_service.criteria.education.EducationCriteria;
import org.khasanof.auth_service.dto.auth_info.AuthInfoCreateDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoDetailDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoGetDTO;
import org.khasanof.auth_service.dto.auth_info.AuthInfoUpdateDTO;
import org.khasanof.auth_service.dto.education.EducationCreateDTO;
import org.khasanof.auth_service.dto.education.EducationUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;
import org.khasanof.auth_service.service.GenericGDLService;
import org.khasanof.auth_service.service.GenericUtilService;

import java.util.List;

public interface AuthInfoService extends
        GenericCUDService<AuthInfoCreateDTO, AuthInfoUpdateDTO, String>,
        GenericGDLService<AuthInfoGetDTO, AuthInfoDetailDTO, String, AuthInfoCriteria>,
        GenericUtilService<AuthInfoGetDTO, AuthInfoSearchCriteria, AuthInfoSearchCriteria>,
        BaseService {

}
