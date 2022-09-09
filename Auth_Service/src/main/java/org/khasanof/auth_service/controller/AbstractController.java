package org.khasanof.auth_service.controller;

import org.khasanof.auth_service.service.BaseService;

public abstract class AbstractController<S extends BaseService> {
    protected S service;
}
