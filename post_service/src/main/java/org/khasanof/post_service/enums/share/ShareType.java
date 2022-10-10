package org.khasanof.post_service.enums.share;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ShareType {
    TELEGRAM("TELEGRAM"),
    INSTAGRAM("INSTAGRAM"),
    FACEBOOK("FACEBOOK"),
    LINKEDIN("LINKEDIN");
    private final String value;

    public static boolean hasReport(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equalsIgnoreCase(var)
                );
    }
}
