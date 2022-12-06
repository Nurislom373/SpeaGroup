package org.khasanof.question_service.service;

import lombok.RequiredArgsConstructor;
import org.khasanof.question_service.mapper.BaseMapper;
import org.khasanof.question_service.repository.BaseRepository;
import org.khasanof.question_service.validator.BaseValidator;

@RequiredArgsConstructor
public abstract class AbstractService<R extends BaseRepository, M extends BaseMapper, V extends BaseValidator> {

    protected final R repository;
    protected final M mapper;
    protected final V validator;

}
