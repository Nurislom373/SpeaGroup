package org.khasanof.post_service.enums.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RatingPointEnum {
    LIKE(10),
    SAVE(5),
    VIEW(1),
    SHARE(7);
    private final Integer point;
}
