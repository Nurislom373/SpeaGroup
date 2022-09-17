package org.khasanof.auth_service.enums.auth_user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthUserStatusEnum {
    ACTIVE("ACTIVE"),
    NO_ACTIVE("NO_ACTIVE"),
    BLOCKED("BLOCKED");
    private final String value;

    public static boolean hasStatus(String var) {
        for (AuthUserStatusEnum value : values()) {
            if (value.value.equalsIgnoreCase(var.toUpperCase())) return true;
        }
        return false;
    }
}
