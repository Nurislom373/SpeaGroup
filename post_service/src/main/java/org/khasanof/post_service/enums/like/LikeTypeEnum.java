package org.khasanof.post_service.enums.like;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LikeTypeEnum {
    LIKE("LIKE"),
    DISLIKE("DISLIKE");
    private final String value;

    public static boolean hasLikeType(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
