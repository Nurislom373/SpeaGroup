package org.khasanof.auth_service.enums.auth_info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum AuthInfoVisibilityEnum {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");
    private final String value;

    public static boolean hasVisibility(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equalsIgnoreCase(var)
                );
    }
}
