package org.khasanof.question_service.service;

import org.khasanof.question_service.criteria.BaseCriteria;
import org.khasanof.question_service.dto.GenericDTO;

import java.io.Serializable;
import java.util.List;

public interface GenericGDLService<GD extends GenericDTO, DD extends GenericDTO, K extends Serializable, CR extends BaseCriteria> {

    GD get(K id);

    DD detail(K id);

    List<GD> list(CR criteria);
}
