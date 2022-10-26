package org.khasanof.auth_service.controller.auth_token;

import org.khasanof.auth_service.controller.AbstractController;
import org.khasanof.auth_service.service.auth_token.AuthTokenRedisService;
import org.khasanof.auth_service.utils.BaseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = BaseUtils.PATH + "/auth_redis_token/*")
public class AuthTokenRedisController extends AbstractController<AuthTokenRedisService> {

    public AuthTokenRedisController(AuthTokenRedisService service) {
        super(service);
    }
}
