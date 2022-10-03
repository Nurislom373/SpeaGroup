package org.khasanof.post_service.enums.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostRatingLastUpdateTypeEnum {
    ADD_LIKE("ADD_LIKE"),
    ADD_VIEW("ADD_VIEW"),
    ADD_SAVE("ADD_SAVE"),
    REMOVE_LIKE("REMOVE_LIKE"),
    REMOVE_SAVE("REMOVE_SAVE");
    private final String value;
}
