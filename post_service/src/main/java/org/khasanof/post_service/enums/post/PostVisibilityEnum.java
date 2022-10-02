package org.khasanof.post_service.enums.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum PostVisibilityEnum {
    PRIVATE("PRIVATE"),
    ACCESS_ONLY("ACCESS_ONLY"),
    FOLLOWER_ONLY("FOLLOWER_ONLY"),
    PUBLIC("PUBLIC");
    private final String value;

    public static boolean hasVisibility(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
