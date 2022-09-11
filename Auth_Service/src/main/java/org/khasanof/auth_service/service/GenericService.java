package org.khasanof.auth_service.service;

import org.khasanof.auth_service.criteria.BaseCriteria;
import org.khasanof.auth_service.criteria.GenericCriteria;
import org.khasanof.auth_service.dto.GenericDTO;

import java.io.Serializable;
import java.util.List;

public interface GenericService<CD extends GenericDTO, UD extends GenericDTO, GD extends GenericDTO, K extends Serializable, CR extends BaseCriteria, GC extends GenericCriteria> {

    void create(CD dto);

    void update(UD dto);

    void delete(K id);

    GD get(K id);

    List<GD> getAll(CR criteria);

    List<GD> getAll(GC genericCriteria);

    long count();
}
