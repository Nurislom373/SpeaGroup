package org.khasanof.auth_service.service.auth_block;

import org.khasanof.auth_service.criteria.auth_block.AuthBlockCriteria;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockDetailDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockGetDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthBlockService extends
        GenericCUDService<AuthBlockCreateDTO, AuthBlockUpdateDTO, String>,
        GenericGDLService<AuthBlockGetDTO, AuthBlockDetailDTO, String, AuthBlockCriteria>,
        BaseService {

}
