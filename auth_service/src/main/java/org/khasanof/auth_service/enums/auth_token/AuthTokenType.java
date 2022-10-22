package org.khasanof.auth_service.enums.auth_token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthTokenType {
    ACCESS("ACCESS"),
    REFRESH("REFRESH");
    private final String value;
}
