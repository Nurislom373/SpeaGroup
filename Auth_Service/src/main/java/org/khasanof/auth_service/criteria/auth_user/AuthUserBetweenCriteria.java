package org.khasanof.auth_service.criteria.auth_user;

import org.khasanof.auth_service.criteria.BetweenCriteria;

public class AuthUserBetweenCriteria extends BetweenCriteria {
    public AuthUserBetweenCriteria(String key, Integer from, Integer to) {
        super(key, from, to);
    }
}
