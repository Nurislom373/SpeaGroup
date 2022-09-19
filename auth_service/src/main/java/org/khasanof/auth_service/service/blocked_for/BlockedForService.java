package org.khasanof.auth_service.service.blocked_for;

import org.khasanof.auth_service.criteria.blocked_for.BlockedForCriteria;
import org.khasanof.auth_service.dto.blocked_for.BlockedForCreateDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForGetDTO;
import org.khasanof.auth_service.dto.blocked_for.BlockedForUpdateDTO;
import org.khasanof.auth_service.service.BaseService;
import org.khasanof.auth_service.service.GenericCUDService;

import java.util.List;

public interface BlockedForService extends GenericCUDService<BlockedForCreateDTO, BlockedForUpdateDTO, String>, BaseService {

    BlockedForGetDTO get(String id);

    List<BlockedForGetDTO> list(BlockedForCriteria criteria);

    Long count();
}
