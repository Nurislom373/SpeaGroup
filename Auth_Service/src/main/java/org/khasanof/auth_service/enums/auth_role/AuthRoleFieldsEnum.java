package org.khasanof.auth_service.enums.auth_role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthRoleFieldsEnum {
    ROLE("role");
    private final String value;
}
