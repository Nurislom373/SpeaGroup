package org.khasanof.auth_service.criteria.auth_info;

import org.khasanof.auth_service.criteria.BetweenCriteria;

public class AuthInfoBetweenCriteria extends BetweenCriteria {
    public AuthInfoBetweenCriteria(String key, Integer from, Integer to) {
        super(key, from, to);
    }
}
