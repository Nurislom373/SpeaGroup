package org.khasanof.auth_service.service;

import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.dto.GenericDTO;

import java.util.List;

public interface GenericUtilService<GD extends GenericDTO, SC extends GenericCriteria, BC extends GenericCriteria> {

    long count();

    List<GD> listWithSc(SC searchCriteria);

    List<GD> listWithBc(BC BetweenCriteria);
}
