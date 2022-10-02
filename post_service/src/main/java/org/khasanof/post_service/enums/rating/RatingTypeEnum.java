package org.khasanof.post_service.enums.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RatingTypeEnum {
    NEW("NEW"),
    RECOMMENDED("RECOMMENDED"),
    POPULAR("POPULAR"),
    LOW("LOW");
    private final String value;

    public static boolean hasRating(String var) {
        return Arrays.stream(values())
                .anyMatch(
                        obj -> obj.value.equals(var)
                );
    }
}
