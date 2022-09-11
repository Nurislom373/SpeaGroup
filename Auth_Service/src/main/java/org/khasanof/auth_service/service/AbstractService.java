package org.khasanof.auth_service.service;

import lombok.RequiredArgsConstructor;
import org.khasanof.auth_service.mapper.BaseMapper;
import org.khasanof.auth_service.repository.BaseRepository;
import org.khasanof.auth_service.validator.Validator;

@RequiredArgsConstructor
public class AbstractService <R extends BaseRepository, M extends BaseMapper, V extends Validator>{
    protected final R repository;
    protected final M mapper;
    protected final V validator;

}
