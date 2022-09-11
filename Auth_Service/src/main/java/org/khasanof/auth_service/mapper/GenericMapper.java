package org.khasanof.auth_service.mapper;

import org.khasanof.auth_service.dto.BaseDTO;
import org.khasanof.auth_service.dto.GenericDTO;
import org.khasanof.auth_service.entity.BaseEntity;

import java.util.List;

public interface GenericMapper<
        E extends BaseEntity,
        D extends GenericDTO,
        CD extends BaseDTO,
        UD extends GenericDTO> extends BaseMapper {
    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD d);

}
