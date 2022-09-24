package org.khasanof.auth_service.service.auth_follower;

import org.khasanof.auth_service.criteria.auth_follower.AuthFollowerCriteria;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerCreateDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerDetailDTO;
import org.khasanof.auth_service.dto.auth_follower.AuthFollowerGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthFollowerService extends GenericGDLService<AuthFollowerGetDTO, AuthFollowerDetailDTO, String, AuthFollowerCriteria>, BaseService {

    void create(AuthFollowerCreateDTO dto);

    void delete(String id);

}
