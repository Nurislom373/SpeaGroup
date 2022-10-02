package org.khasanof.post_service.enums.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PostStatusEnum {
    ACTIVE("ACTIVE"),
    NO_ACTIVE("NO_ACTIVE"),
    BLOCKED("BLOCKED");
    private final String value;

    public static boolean hasStatus(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
