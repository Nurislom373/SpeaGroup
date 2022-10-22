package org.khasanof.auth_service.enums.auth_token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthTokenFieldsEnum {
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt"),
    DURATION("duration"),
    TYPE("type");
    private final String value;
}
