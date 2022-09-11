package org.khasanof.auth_service.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.khasanof.auth_service.service.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;

    protected final String API = "/api";
    protected final String VERSION = "/v1";
    protected final String PATH = API + VERSION;
}
