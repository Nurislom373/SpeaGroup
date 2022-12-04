package org.khasanof.question_service.service;

import lombok.RequiredArgsConstructor;
import org.khasanof.post_service.mapper.BaseMapper;
import org.khasanof.post_service.repository.BaseRepository;
import org.khasanof.post_service.validator.BaseValidator;

@RequiredArgsConstructor
public abstract class AbstractService<R extends BaseRepository, M extends BaseMapper, V extends BaseValidator> {

    protected final R repository;
    protected final M mapper;
    protected final V validator;

}
