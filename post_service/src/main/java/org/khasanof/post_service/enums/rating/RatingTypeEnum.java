package org.khasanof.post_service.enums.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RatingTypeEnum {
    POPULAR("POPULAR", 10000),
    RECOMMENDED("RECOMMENDED", 1000),
    NORMAL("NORMAL", 100),
    NEW("NEW", 0),
    LOW("LOW", 0);
    private final String value;
    private final Integer point;

    public static boolean hasRating(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
