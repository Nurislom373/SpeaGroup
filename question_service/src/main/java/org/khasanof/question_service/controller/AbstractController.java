package org.khasanof.question_service.controller;

import lombok.RequiredArgsConstructor;
import org.khasanof.post_service.service.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    public static final String PATH = API + VERSION;
}
