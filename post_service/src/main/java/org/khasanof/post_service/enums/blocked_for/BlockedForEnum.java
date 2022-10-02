package org.khasanof.post_service.enums.blocked_for;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum BlockedForEnum {
    REPORT("REPORT"),
    BAD_CONTENT("BAD_CONTENT"),
    DONT_USE_WORDS("DONT_USE_WORDS");
    private final String value;

    public static boolean hasBlockedFor(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
