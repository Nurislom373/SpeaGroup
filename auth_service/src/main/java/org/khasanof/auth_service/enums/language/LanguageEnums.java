package org.khasanof.auth_service.enums.language;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Getter
@RequiredArgsConstructor
public enum LanguageEnums {
    ENGLISH("en"),
    RUSSIA("ru"),
    UZBEK("uz");
    private final String value;

    public static boolean hasLang(String var) {
        for (LanguageEnums value : values()) {
            if (value.value.equals(var.toLowerCase(Locale.ROOT))) return true;
        }
        return false;
    }
}
