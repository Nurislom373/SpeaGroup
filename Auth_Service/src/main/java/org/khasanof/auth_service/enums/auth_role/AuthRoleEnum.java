package org.khasanof.auth_service.enums.auth_role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum AuthRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");
    private final String value;

    public static boolean hasRole(String value) {
        for (AuthRoleEnum authRoleEnum : values()) {
            if (authRoleEnum.value.equals(value.toUpperCase(Locale.ROOT))) return true;
        }
        return false;
    }
}
