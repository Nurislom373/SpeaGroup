package org.khasanof.auth_service.enums.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum LanguageEnums {
    ENGLISH("en"),
    RUSSIA("ru"),
    UZBEK("uz");
    private final String value;

    public static boolean hasLang(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equalsIgnoreCase(var)
                );
    }
}
