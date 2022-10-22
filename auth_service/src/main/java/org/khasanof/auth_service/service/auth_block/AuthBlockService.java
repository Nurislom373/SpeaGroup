package org.khasanof.auth_service.service.auth_block;

import org.khasanof.auth_service.criteria.auth_block.AuthBlockCriteria;
import org.khasanof.auth_service.dto.auth_block.AuthBlockCreateDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockDetailDTO;
import org.khasanof.auth_service.dto.auth_block.AuthBlockGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthBlockService extends
        GenericGDLService<AuthBlockGetDTO, AuthBlockDetailDTO, String, AuthBlockCriteria>,
        BaseService {

    void create(AuthBlockCreateDTO dto);

    void delete(String id);

    void autoDeleteTimeOut();

}
