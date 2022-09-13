package org.khasanof.auth_service.enums.auth_user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthUserFieldsEnum {
    FIRST_NAME("first_name"),
    USERNAME("username"),
    EMAIL("email"),
    LANGUAGE("language"),
    STATUS("status"),
    LAST_NAME("last_name");
    private final String value;
}
