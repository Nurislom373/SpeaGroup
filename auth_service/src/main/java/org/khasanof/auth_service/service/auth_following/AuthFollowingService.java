package org.khasanof.auth_service.service.auth_following;

import org.khasanof.auth_service.criteria.auth_following.AuthFollowingCriteria;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingCreateDTO;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingDetailDTO;
import org.khasanof.auth_service.dto.auth_following.AuthFollowingGetDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericGDLService;

public interface AuthFollowingService extends GenericGDLService<AuthFollowingGetDTO, AuthFollowingDetailDTO, String, AuthFollowingCriteria>, BaseService {

    void create(AuthFollowingCreateDTO dto);

    void delete(String id);

    void deleteFollowing(String id, String userId);

}
