package org.khasanof.auth_service.enums.auth_token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthTokenEnum {
    DURATION("duration");
    private final String value;
}
